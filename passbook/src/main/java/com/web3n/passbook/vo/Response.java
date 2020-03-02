package com.web3n.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * Created by macro on Response.
 * Controller 统一的响应
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    /** 错误码：正常返回 0 */
    private Integer errorCode = 0;
    /** 错误信息， 正确返回空字符串 */
    private String errorMsg = "";
    /** 返回值对象 */
    private Object data = new HashMap<String, String>();
    /** 正确的响应构造函数 */
    public Response(Object data){
        this.data = data;
    }
    /** 错误的响应构造函数 */
    public Response(Integer errorCode, String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    /** 正确的 空响应 */
    public static Response success(){
        return new Response();
    }
    /** 错误响应 */
    public static Response failure(String errorMsg){
        return new Response(-1, errorMsg);
    }
}
