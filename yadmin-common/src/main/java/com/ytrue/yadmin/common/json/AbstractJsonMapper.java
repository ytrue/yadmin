package com.ytrue.yadmin.common.json;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ytrue
 * @date 2021/4/20 13:34
 * @description mapper 的工厂
 */
@Slf4j
public abstract class AbstractJsonMapper implements JsonMapper {


    private static final String CLASS_TYPE_JACKSON = "com.fasterxml.jackson.databind.ObjectMapper";
    private static final String CLASS_TYPE_FASTJSON = "com.alibaba.fastjson.JSON";
    private static final String CLASS_TYPE_GSON = "com.google.gson.Gson";

    /**
     * 初始化 JsonMapper
     *
     * @param jsonEnum
     * @return
     */
    public static AbstractJsonMapper initJsonMapper(JsonEnum jsonEnum) {
        switch (jsonEnum) {
            case JACKSON:
                if (isPresent(CLASS_TYPE_JACKSON)) {
                    return new JacksonJsonMapper();
                } else {
                    log.error("jackson not found");
                }
            case FASTJSON:
                if (isPresent(CLASS_TYPE_FASTJSON)) {
                    return new FastJsonMapper();
                } else {
                    log.error("fastjson not found");
                }
            case GSON:
                if (isPresent(CLASS_TYPE_GSON)) {
                    return new GsonMapper();
                } else {
                    log.error("gson not found");
                }
            default:
                throw new RuntimeException("未找到jackson、gson或fastjson的依赖");
        }
    }

    /**
     * 是否导入了jar
     *
     * @param className
     * @return
     */
    private static boolean isPresent(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (IllegalAccessError err) {
            throw new IllegalStateException("Readability mismatch in inheritance hierarchy of class [" +
                    className + "]: " + err.getMessage(), err);
        } catch (Throwable ex) {
            //通常是ClassNotFoundException或NoClassDefFoundError ...
            return false;
        }
    }
}
