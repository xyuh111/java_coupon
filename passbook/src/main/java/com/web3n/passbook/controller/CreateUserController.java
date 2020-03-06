package com.web3n.passbook.controller;

import com.web3n.passbook.log.LogConstants;
import com.web3n.passbook.log.LogGenerator;
import com.web3n.passbook.service.IUserService;
import com.web3n.passbook.vo.Response;
import com.web3n.passbook.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @create 2020-03-06 11:28
 * 创建用户服务
 */
@Slf4j
@RestController
@RequestMapping("/passbook")
public class CreateUserController {
    /** 创建用户服务 */
    private final IUserService userService;
    
    /** HttpServletRequest */
    private final HttpServletRequest httpServletRequest;
    
    @Autowired
    public CreateUserController(IUserService userService, HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.httpServletRequest = httpServletRequest;
    }
    
    /**
     * 创建用户
     * @param user {@link User}
     * @return {@link Response}
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/createUser")
    Response createUser(@RequestBody User user) throws Exception{
        LogGenerator.genLog(
                httpServletRequest,
                -1L,
                LogConstants.ActionName.CREATE_USER,
                user
        );
        return userService.createUser(user);
    }
}
