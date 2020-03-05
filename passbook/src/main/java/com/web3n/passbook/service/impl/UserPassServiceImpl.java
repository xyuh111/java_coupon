package com.web3n.passbook.service.impl;

import com.alibaba.fastjson.JSON;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import com.web3n.passbook.constant.Constants;
import com.web3n.passbook.constant.PassStatus;
import com.web3n.passbook.dao.MerchantsDao;
import com.web3n.passbook.entity.Merchants;
import com.web3n.passbook.mapper.PassRowMapper;
import com.web3n.passbook.service.IUserPassService;
import com.web3n.passbook.vo.Pass;
import com.web3n.passbook.vo.PassInfo;
import com.web3n.passbook.vo.PassTemplate;
import com.web3n.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @create 2020-03-05 9:57
 * 用户优惠券相关功能实现
 */
@Slf4j
@Service
public class UserPassServiceImpl implements IUserPassService {
    
    /** HBase 客户端 */
    @Autowired
    private HbaseTemplate hbaseTemplate;
    
    /** MerchantsDao */
    @Autowired
    private MerchantsDao merchantsDao;
    
    @Override
    public Response getUserPassInfo(Long userId) throws Exception {
        return getPassInfoByStatus(userId, PassStatus.UNUSED);
    }
    
    @Override
    public Response getUserUsedInfo(Long userId) throws Exception {
        return getPassInfoByStatus(userId, PassStatus.USED);
    }
    
    @Override
    public Response getUserAllPassInfo(Long userId) throws Exception {
        return getPassInfoByStatus(userId, PassStatus.ALL);
    }
    
    @Override
    public Response userUsePass(Pass pass) {
        /** 根据 userId 构建行键前缀 */
        byte[] rowPrefix = Bytes.toBytes(new StringBuilder(String.valueOf(pass.getUserId())).reverse().toString());
        Scan scan = new Scan();
        List<Filter> filters = new ArrayList<>();
        filters.add(new PrefixFilter(rowPrefix));
        filters.add(new SingleColumnValueFilter(
                Constants.PassTable.FAMILY_I.getBytes(),
                Constants.PassTable.TEMPLATE_ID.getBytes(),
                CompareFilter.CompareOp.EQUAL,
                Bytes.toBytes(pass.getTemplateId())
        ));
        filters.add(new SingleColumnValueFilter(
                Constants.PassTable.FAMILY_I.getBytes(),
                Constants.PassTable.CON_DATE.getBytes(),
                CompareFilter.CompareOp.EQUAL,
                Bytes.toBytes("-1")
        ));
        List<Pass> passes = hbaseTemplate.find(Constants.PassTable.TABLE_NAME, scan, new PassRowMapper());
        if(null == passes || passes.size() != 1){
            log.error("UserUsePass Error : {}", JSON.toJSONString(pass));
            return Response.failure("UserUsePass Error");
        }
        byte[] FAMILY_I = Constants.PassTable.FAMILY_I.getBytes();
        byte[] CON_DATE = Constants.PassTable.CON_DATE.getBytes();
        
        List<Mutation> datas = new ArrayList<>();
        Put put = new Put(passes.get(0).getRowKey().getBytes());
        put.addColumn(
                FAMILY_I,
                CON_DATE,
                Bytes.toBytes(DateFormatUtils.ISO_DATE_FORMAT.format(new Date()))
        );
        datas.add(put);
        hbaseTemplate.saveOrUpdates(Constants.PassTable.TABLE_NAME, datas);
        return Response.success();
    }
    
    /**
     * 根据优惠卷状态获取优惠券信息
     * @param userId 用户 di
     * @param status  {@link PassStatus}
     * @return {@link Response}
     */
    private Response getPassInfoByStatus(Long userId, PassStatus status) throws Exception{
        /** 根据 userId 构造行键前缀 */
        byte[] rowPrefix = Bytes.toBytes(new StringBuffer(String.valueOf(userId)).reverse().toString());
        CompareFilter.CompareOp compareOp = status == PassStatus.UNUSED ?
                CompareFilter.CompareOp.EQUAL : CompareFilter.CompareOp.NO_OP;
        Scan scan = new Scan();
        /** 1. 行键前缀过滤器，找到特定用户的优惠券 */
        List<Filter> filters = new ArrayList<>();
        filters.add(new PrefixFilter(rowPrefix));
        /** 2. 基于列单元值过滤器，找到未使用的优惠券 */
        if(status != PassStatus.ALL){
            filters.add(new SingleColumnValueFilter(Constants.PassTable.FAMILY_I.getBytes(),
                    Constants.PassTable.CON_DATE.getBytes(),
                    compareOp, Bytes.toBytes("-1")));
        }
        scan.setFilter(new FilterList(filters));
        
        List<Pass> passes = hbaseTemplate.find(Constants.PassTable.TABLE_NAME, scan, new PassRowMapper());
        Map<String, PassTemplate> passTemplateMap = buildPassTemplateMap(passes);
        Map<Integer, Merchants> merchantsMap = buildMerchantsMap(new ArrayList<>(passTemplateMap.values()));
        
        List<PassInfo> result = new ArrayList<>();
        for (Pass pass : passes) {
            PassTemplate passTemplate = passTemplateMap.getOrDefault(pass.getTemplateId(), null);
            if(null == passTemplate){
                log.error("PassTemplate Null : {}", pass.getTemplateId());
                continue;
            }
            Merchants merchants = merchantsMap.getOrDefault(passTemplate.getId(), null);
            if(null == merchants){
                log.error("Merchants Null : {}", passTemplate.getId());
                continue;
            }
            result.add(new PassInfo(pass, passTemplate, merchants));
        }
        return new Response(result);
    };
    
    /**
     * 通过获取的 Pass 对象构造 Map
     * @param passes {@link Pass}
     * @return {@link PassTemplate}
     * @throws Exception
     */
    private Map<String, PassTemplate> buildPassTemplateMap(List<Pass> passes) throws Exception{
        List<String> templateIds = passes.stream().map(
                Pass::getTemplateId
        ).collect(Collectors.toList());
        
        List<Get> templateGets = new ArrayList<>(templateIds.size());
        templateIds.forEach(t -> templateGets.add(new Get(Bytes.toBytes(t))));
    
        Result[] templateResults = hbaseTemplate.getConnection()
                .getTable(TableName.valueOf(Constants.PassTemplateTable.TABLE_NAME))
                .get(templateGets);
        
        // 构造 PassTemplate  ->  PassTemplate Object 的 Map， 用于构造 PassInfo
        Map<String, PassTemplate> templateId2Object = new HashMap<>();
        for (Result item : templateResults) {
            PassTemplate  passTemplate = PassTemplate.toPassTemplate(item);
            templateId2Object.put(Bytes.toString(item.getRow()), passTemplate);
        }
        return templateId2Object;
    }
    
    /**
     * 通过获取的 PassTemplate 对象构造 Merchants
     * @param passTemplates {@link PassTemplate}
     * @return {@link Merchants}
     */
    private Map<Integer, Merchants> buildMerchantsMap(List<PassTemplate> passTemplates){
        Map<Integer, Merchants> merchantsMap = new HashMap<>();
        List<Integer> merchantsIds = passTemplates.stream().map(
                PassTemplate::getId
        ).collect(Collectors.toList());
        List<Merchants> merchants = merchantsDao.findAllById(merchantsIds);
        merchants.forEach(m -> merchantsMap.put(m.getId(), m));
        return merchantsMap;
    }
}
