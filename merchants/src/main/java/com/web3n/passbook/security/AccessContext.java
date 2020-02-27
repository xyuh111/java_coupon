package com.web3n.passbook.security;

/**
 * 用 ThreadLocal 单独存储每一个线程携带的 Token 信息
 * Created by macro on AccessContext.
 **/
public class AccessContext {
    private static final ThreadLocal<String> token = new ThreadLocal<>();

    public static String getToken() {
        return token.get();
    }
    public static void setToken(String tokenStr){
        token.set(tokenStr);
    }
    public static void clearAccessKey(){
        token.remove();
    }
}
