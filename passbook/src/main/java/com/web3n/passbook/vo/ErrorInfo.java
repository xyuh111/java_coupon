package com.web3n.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * Created by macro on ErrorInfo.
 * 统一的错误信息
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo<T> {
    /** 错误码 */
    public static final Integer ERROR = -1;
    /** 特定错误码 */
    private Integer code;
    /** 错误信息 */
    private String message;
    /** 请求 url */
    private String url;
    /** 请求返回的数据 */
    private Object data = new HashMap<String, String>();
}
