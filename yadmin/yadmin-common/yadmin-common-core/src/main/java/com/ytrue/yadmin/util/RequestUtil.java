package com.ytrue.yadmin.util;


import javax.servlet.http.HttpServletRequest;

/**
 * @author ytrue
 * @date 2021/10/18 17:42
 * @description 请求工具类
 */
public class RequestUtil {


    /**
     * 获得token
     *
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header.substring(header.indexOf("bearer") + 7);
    }
}
