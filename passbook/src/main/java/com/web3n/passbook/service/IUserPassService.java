package com.web3n.passbook.service;

import com.web3n.passbook.vo.Pass;
import com.web3n.passbook.vo.Response;

/**
 * @create 2020-03-03 15:53
 * 获取用户个人优惠卷信息
 */
public interface IUserPassService {
    /**
     * 获取用户个人优惠卷信息，即我的优惠卷功能实现
     * @param userId 用户id
     * @return {@link Response}
     * @throws Exception
     */
    Response getUserPassInfo(Long userId) throws Exception;
    
    /**
     * 获取用户已经消费了的优惠卷，即已使用优惠卷功能实现
     * @param userId 用户id
     * @return {@link Response}
     * @throws Exception
     */
    Response getUserUsedInfo(Long userId) throws Exception;
    
    /**
     * 获取用户所有的优惠券
     * @param userId 用户 id
     * @return {@link Response}
     * @throws Exception
     */
    Response getUserAllPassInfo(Long userId) throws Exception;
    
    /**
     * 用户使用优惠券
     * @param pass {@link Pass}
     * @return {@link Response}
     */
    Response userUsePass(Pass pass);
}
