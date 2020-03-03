package com.web3n.passbook.service;

import com.web3n.passbook.vo.GainPassTemplateRequest;
import com.web3n.passbook.vo.Response;

/**
 * @create 2020-03-03 15:50
 * 用户领取优惠卷功能实现
 */
public interface IGainPassTemplateService {
    /**
     * 用户领取优惠卷
     * @param request {@link GainPassTemplateRequest}
     * @return {@link Response}
     * @throws Exception
     */
    Response gainPassTemplate(GainPassTemplateRequest request) throws Exception;
}
