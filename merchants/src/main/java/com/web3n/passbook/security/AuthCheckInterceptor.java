package com.web3n.passbook.security;

import com.web3n.passbook.constant.Constants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器 （拦截所以的 http 请求）
 * Created by macro on AuthCheckInterceptor.
 **/
@Component
public class AuthCheckInterceptor implements HandlerInterceptor {
    /**
     * http 真正处理之前 拦截处理
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        String token = req.getHeader(Constants.TOKEN_STRING);
        if(StringUtils.isEmpty(token)){
            throw new Exception("Header 中缺少 " + Constants.TOKEN_STRING + " ！");
        };
        if(!token.equals(Constants.TOKEN)){
            throw new Exception("Header 中 " + Constants.TOKEN_STRING + " 错误！");
        }
        AccessContext.setToken(token);
        return true;
    }

    /**
     * http 业务处理完成之后 拦截处理（如果抛出异常不会触发，一般不会在这里完成一些信息）
     */
    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {

    }
    /**
     * http 业务处理完成之后 拦截处理（抛出异常也会触发）
     * 做一些清理工作
     */
    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) throws Exception {
        AccessContext.clearAccessKey();
    }
}
