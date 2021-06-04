package com.ytrue.yadmin.common.json;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author ytrue
 * @date 2021/4/20 13:34
 * @description JsonMapper接口
 */
public interface JsonMapper {
    <K, V> Map<K, V> toMap(String json);

    <K, V> Map<K, V> toMap(String json, Type type);

    <T> List<T> toList(String json);

    <T> List<T> toList(String json, Type type);

    String toJsonString(Object object);

    String toJsonString(Object object, String dateFormatPattern);

    <T> T toObject(String json, Class<T> valueType);

    <K, V> Map<K, V> objectToMap(Object fromValue);

    <T> T mapToObject(Map fromMap, Class<T> toValueType);
}
