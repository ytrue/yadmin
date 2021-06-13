package com.ytrue.yadmin.tool.oss.factory;

import com.google.common.collect.Maps;
import com.ytrue.yadmin.tool.oss.cloud.AbstractStrategyUpload;
import org.springframework.util.StringUtils;

import java.util.Map;


/**
 * @author ytrue
 * @date 2021/6/13 18:58
 * @description 工厂设计模式
 */
public class StrategyUploadFactory {

    private static final Map<String, AbstractStrategyUpload> UPLOAD_MAP = Maps.newHashMap();

    public static AbstractStrategyUpload getInvokeStrategy(String str) {
        return UPLOAD_MAP.get(str);
    }

    public static void register(String str, AbstractStrategyUpload handler) {
        if (StringUtils.isEmpty(str) || null == handler) {
            return;
        }
        UPLOAD_MAP.put(str, handler);
    }

}
