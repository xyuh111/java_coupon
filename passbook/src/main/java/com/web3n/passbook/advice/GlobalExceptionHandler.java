package com.web3n.passbook.advice;

import com.web3n.passbook.vo.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by macro on GlobalExceptionHandler.
 * 全局异常处理
 **/
/** @ControllerAdvice 注解会被自动注入到包中 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)/** 所有抛出的异常都会进入到这个方法 */
    public ErrorInfo<String> errorHandler(HttpServletRequest req, HttpServletResponse res, Exception ex){
        ErrorInfo<String> info = new ErrorInfo<String>();
        info.setCode(ErrorInfo.ERROR);
        info.setMessage(ex.getMessage());
        info.setUrl(req.getRequestURI());
        return info;
    };
}
