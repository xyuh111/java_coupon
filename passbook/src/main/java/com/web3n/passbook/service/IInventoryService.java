package com.web3n.passbook.service;

import com.web3n.passbook.vo.Response;

/**
 * Created by macro on IInventoryService.
 * 获取库存信息：只返回用户没有领取的，即优惠卷库存功能接口定义
 **/
public interface IInventoryService {
    /**
     * 获取库存信息
     * @param userId 用户 id
     * @return {@link Response}
     * @throws Exception
     */
    Response getInventoryInfo(Long userId) throws Exception;
}




