package com.ytrue.yadmin.common.json;

import lombok.Getter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author ytrue
 * @date 2021/4/20 13:34
 * @description TypeReference
 */
@Getter
public abstract class TypeReference<T> implements Comparable<TypeReference<T>> {

    private final Type type;

    public TypeReference() {
        Type superClass = getClass().getGenericSuperclass();
        // 健全性检查，永远不要发生
        if (superClass instanceof Class<?>) {
            throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information");
        }
        type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }


    @Override
    public int compareTo(TypeReference<T> o) {
        return 0;
    }
}
