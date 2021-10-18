package com.ytrue.yadmin.util;

import cn.hutool.http.server.HttpServerRequest;

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
    public static String getToken(HttpServerRequest request) {
        String header = request.getHeader("Authorization");
        return header.substring(header.indexOf("bearer") + 7);
    }
}
