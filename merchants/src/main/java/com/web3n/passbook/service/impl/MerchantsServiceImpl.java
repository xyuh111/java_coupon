package com.web3n.passbook.service.impl;

import com.alibaba.fastjson.JSON;
import com.web3n.passbook.constant.Constants;
import com.web3n.passbook.constant.ErrorCode;
import com.web3n.passbook.dao.MerchantsDao;
import com.web3n.passbook.entity.Merchants;
import com.web3n.passbook.service.IMerchantsService;
import com.web3n.passbook.vo.CreateMerchantsRequest;
import com.web3n.passbook.vo.CreateMerchantsResponse;
import com.web3n.passbook.vo.PassTemplate;
import com.web3n.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 商户服务接口实现
 * Created by macro on MerchantsServiceImpl.
 **/
@Slf4j  /** 注解在类上；为类提供一个 属性名为log 的 log4j 日志对像  和 @Slf4j 一样  */
@Service
public class MerchantsServiceImpl implements IMerchantsService {
    /** Merchants 数据库接口 */
    @Autowired
    private MerchantsDao merchantsDao;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    @Transactional /** 添加事务， 操作成功则提交，操作败便回滚 */
    public Response createMerchants(CreateMerchantsRequest request) {
        Response response = new Response();
        CreateMerchantsResponse merchantsResponse = new CreateMerchantsResponse();
        ErrorCode errorCode = request.validate(merchantsDao);
        if(errorCode != ErrorCode.SUCCESS){
            merchantsResponse.setId(-1);
            response.setErrorCode(errorCode.getCode());
            response.setErrorMas(errorCode.getDesc());
        } else {
          merchantsResponse.setId(merchantsDao.save(request.toMerchants()).getId());
        }
        response.setData(merchantsResponse);
        return response;
    }

    @Override
    public Response buildMerchantsInfoById(Integer id) {
        Response response = new Response();
        Optional<Merchants> merchants = merchantsDao.findById(id);
        System.out.println(merchants.isPresent());
        if(false == merchants.isPresent()) {
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrorMas(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
            return response;
        }
        response.setData(merchants.get());
        return response;
    }

    @Override
    public Response dropPassTemplate(PassTemplate template) {
        Response response = new Response();
        ErrorCode errorCode = template.validate(merchantsDao);
        if(errorCode != ErrorCode.SUCCESS){
            response.setErrorMas(errorCode.getDesc());
            response.setErrorCode(errorCode.getCode());
        } else {
            String passTemplate = JSON.toJSONString(template);
            kafkaTemplate.send(
                    Constants.TEMPLATE_TOPIC,
                    Constants.TEMPLATE_TOPIC,
                    passTemplate
            );
            log.info("DropPassTemplates:{}", passTemplate);
        }
        return response;
    }
}
