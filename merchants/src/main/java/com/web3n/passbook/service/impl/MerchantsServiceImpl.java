package com.web3n.passbook.service.impl;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商户服务接口实现
 * Created by macro on MerchantsServiceImpl.
 **/
@Slf4j  /** 注解在类上；为类提供一个 属性名为log 的 log4j 日志对像  和 @Slf4j 一样  */
@Service
public class MerchantsServiceImpl implements IMerchantsService {
    /** Merchants 数据库接口 */
    private final MerchantsDao merchantsDao;

    @Autowired
    public MerchantsServiceImpl(MerchantsDao merchantsDao) {
        this.merchantsDao = merchantsDao;
    }

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
        return null;
    }

    @Override
    public Response dropPassTemplate(PassTemplate template) {
        return null;
    }
}
