package com.web3n.passbook.controller;

import com.alibaba.fastjson.JSON;
import com.web3n.passbook.service.IMerchantsService;
import com.web3n.passbook.vo.CreateMerchantsRequest;
import com.web3n.passbook.vo.PassTemplate;
import com.web3n.passbook.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by macro on MerchantsCtl.
 *商户服务 Controller
 **/
@Slf4j
@RestController /** 使用 rest Api */
@RequestMapping("/merchants")
public class MerchantsCtl {
    @Autowired
    private IMerchantsService merchantsService;

    @ResponseBody /** 返回一个字符串对象 */
    @PostMapping(value = "/createMerchants")
    public Response createMerchants(@RequestBody CreateMerchantsRequest request){
        log.info("CreateMerchants:{}", JSON.toJSONString(request));
        return merchantsService.createMerchants(request);
    };

    @ResponseBody
    @GetMapping("/buildMerchantsInfo/{id}")
    public Response buildMerchantsInfo(@PathVariable Integer id){
        log.info("BuildMerchantsInfo:{}", id);
        return merchantsService.buildMerchantsInfoById(id);
    }

    @ResponseBody /** 返回一个字符串对象 */
    @PostMapping("/dropPassTemplate")
    public Response dropPassTemplate(@RequestBody PassTemplate passTemplate){
        log.info("DropPassTemplate:{}", JSON.toJSONString(passTemplate));
        return merchantsService.dropPassTemplate(passTemplate);
    };
}
