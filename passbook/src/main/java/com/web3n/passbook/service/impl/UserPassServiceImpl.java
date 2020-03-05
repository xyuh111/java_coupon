package com.web3n.passbook.service.impl;

import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import com.web3n.passbook.constant.Constants;
import com.web3n.passbook.dao.MerchantsDao;
import com.web3n.passbook.entity.Merchants;
import com.web3n.passbook.service.IUserPassService;
import com.web3n.passbook.vo.Pass;
import com.web3n.passbook.vo.PassTemplate;
import com.web3n.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private MerchantsDao merchantsDao
    
    @Override
    public Response getUserPassInfo(Long userId) throws Exception {
        return null;
    }
    
    @Override
    public Response getUserUsedInfo(Long userId) throws Exception {
        return null;
    }
    
    @Override
    public Response getUserAllPassInfo(Long userId) throws Exception {
        return null;
    }
    
    @Override
    public Response userUsePass(Pass pass) {
        return null;
    }
    
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
