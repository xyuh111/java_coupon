package com.web3n.passbook.merchants.service;

import com.alibaba.fastjson.JSON;
import com.web3n.passbook.service.IMerchantsService;
import com.web3n.passbook.vo.CreateMerchantsRequest;


import com.web3n.passbook.vo.PassTemplate;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

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
//    @Transactional /** 添加事务 测试成功后，自动实现数据回滚，数据库的数据不会发生变化 */
    public void testCreatMerchantService(){
        CreateMerchantsRequest request = new CreateMerchantsRequest();
        request.setName("张斌4");
        request.setLogoUrl("https://avatars1.githubusercontent.com/u/30693351?v=4");
        request.setPhone("123456789");
        request.setBusinessLicenseUrl("http://blog.web3n.com");
        request.setAddress("深圳");
        System.out.println(JSON.toJSONString(merchantsService.createMerchants(request)));
    }

    /**
     * {"data":{"address":"??","businessLicenseUrl":"http://blog.web3n.com","id":22,"isAudit":false,"logoUrl":"https://avatars1.githubusercontent.com/u/30693351?v=4","name":"??3","phone":"123456789"},"errorCode":0,"errorMas":""}
     */
    @Test
    public void testBuildMerchantsInfoById(){
        System.out.println(JSON.toJSONString(merchantsService.buildMerchantsInfoById(22)));
    }

    @Test
    public void  testDropPassTemplate(){
        PassTemplate passTemplate = new PassTemplate();
        passTemplate.setId(22);
        passTemplate.setTitle("title：优惠劵");
        passTemplate.setSummary("简介：11111");
        passTemplate.setDesc("详情：22222");
        passTemplate.setLimit(10000L);
        passTemplate.setBackground(2);
        passTemplate.setStart(new Date());
        passTemplate.setEnd(DateUtils.addDays(new Date(), 30));
        System.out.println(JSON.toJSONString(merchantsService.dropPassTemplate(passTemplate)));
    }
}

