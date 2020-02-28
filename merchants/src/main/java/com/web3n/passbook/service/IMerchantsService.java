package com.web3n.passbook.service;

import com.web3n.passbook.vo.CreateMerchantsRequest;
import com.web3n.passbook.vo.PassTemplate;
import com.web3n.passbook.vo.Response;

/**
 * 对商户服务接口定义
 * Created by macro on IMerchantsService.
 **/
public interface IMerchantsService {
    /**
     * 创建商户服务
     * @param request {@link CreateMerchantsRequest} 创建商户请求
     * @return {@link Response}
     */
    Response createMerchants(CreateMerchantsRequest request);

    /**
     * 根据 id 构造商户信息
     * @param id 商户 id
     * @return {@link Response}
     */
    Response buildMerchantsInfoById(Integer id);

    /**
     * 投放优惠劵
     * @param template {@link PassTemplate} 优惠劵对象
     * @return {@link Response}
     */
    Response dropPassTemplate(PassTemplate template);
}
