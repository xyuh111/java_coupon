package com.web3n.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的响应对象
 * Created by macro on Response.
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    /** 错误码，正确返回 0 */
    private Integer errorCode = 0;

    /** 错误信息，正确返回空字符串 */
    private String errorMas = "";

    /** 返回值对象 */
    private Object data = new HashMap<String, Object>();
    /**
     * 正确的响应构造函数
     * @param data  返回值对象
     */
    public Response(Object data){
        this.data = data;
    }
}
