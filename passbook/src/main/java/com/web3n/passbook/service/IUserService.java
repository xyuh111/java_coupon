package com.web3n.passbook.service;

import com.web3n.passbook.vo.Response;
import com.web3n.passbook.vo.User;

import java.lang.annotation.Repeatable;

/**
 * Created by macro on IUserService.
 * 用户服务：创建 User 服务
 **/
public interface IUserService {
    /**
     * 创建用户
     * @param user {@link User}
     * @return {@link Response}
     * @throws Exception
     */
    Response createUser(User user)throws Exception;
}
