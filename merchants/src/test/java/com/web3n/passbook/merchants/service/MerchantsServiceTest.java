package com.web3n.passbook.merchants.service;

import com.alibaba.fastjson.JSON;
import com.web3n.passbook.service.IMerchantsService;
import com.web3n.passbook.vo.CreateMerchantsRequest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by macro on MerchantsServiceTest.
 * 商户服务测试类
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MerchantsServiceTest{
    @Resource
    private IMerchantsService merchantsService;

    /**
     * {"data":{"id":17},"errorCode":0,"errorMas":""}
     * {"data":{"id":20},"errorCode":0,"errorMas":""}
     */
    @Test
    @Transactional /** 添加事务 测试成功后，自动实现数据回滚，数据库的数据不会发生变化 */
    public void testCreatMerchantService(){
        CreateMerchantsRequest request = new CreateMerchantsRequest();
        request.setName("张斌3");
        request.setLogoUrl("https://avatars1.githubusercontent.com/u/30693351?v=4");
        request.setPhone("123456789");
        request.setBusinessLicenseUrl("http://blog.web3n.com");
        request.setAddress("深圳");
        System.out.println(JSON.toJSONString(merchantsService.createMerchants(request)));
    }
}

