package com.ytrue.yadmin.oss.factory;

import com.google.common.collect.Maps;
import com.ytrue.yadmin.oss.cloud.AbstractUpload;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentMap;


/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description 工厂设计模式
 */
public class UploadFactory {

    private final static ConcurrentMap<String, AbstractUpload> MAP = Maps.newConcurrentMap();

    /**
     * 获得实例
     *
     * @param str
     * @return
     */
    public static AbstractUpload getInvokeStrategy(String str) {
        return MAP.get(str);
    }

    /**
     * 注册
     *
     * @param str
     * @param handler
     */
    public static void register(String str, AbstractUpload handler) throws RuntimeException {
        if (StringUtils.isEmpty(str) || null == handler) {
            throw new RuntimeException("未定义oss类型");
        }
        MAP.put(str, handler);
    }

}
