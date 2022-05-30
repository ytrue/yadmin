package com.ytrue.yadmin.core.utils;

import com.ytrue.yadmin.core.enums.KeyValueEnum;

/**
 * @author ytrue
 * @date 2022/5/28 19:17
 * @description 枚举常用工具类。<br/>
 * 使用该枚举工具类需要指定的枚举实现
 */
public final class KeyValueEnumUtils {

    /**
     * 判断key 是是否在枚举中
     *
     * @param enums 枚举数组
     * @param key  枚举值
     * @return 是否存在
     */
    public static <K, V> boolean isExistByName(KeyValueEnum<K, V>[] enums, K key) {
        if (key == null) {
            return false;
        }
        for (KeyValueEnum<K, V> e : enums) {
            if (key.equals(e.getKey())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断key 是是否在枚举中
     *
     * @param enumClass 枚举类
     * @param key      枚举值
     * @param <E>       枚举类型
     * @param <K>       值类型
     * @return true：存在
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends KeyValueEnum<K, V>>, K, V> boolean isExistByName(Class<E> enumClass, K key) {
        for (Enum<? extends KeyValueEnum<K, V>> e : enumClass.getEnumConstants()) {
            if (((KeyValueEnum<K, V>) e).getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断 value 是是否在枚举中
     *
     * @param enums 枚举数组
     * @param value 枚举值
     * @return 是否存在
     */
    public static <K, V> boolean isExistByValue(KeyValueEnum<K, V>[] enums, V value) {
        if (value == null) {
            return false;
        }
        for (KeyValueEnum<K, V> e : enums) {
            if (value.equals(e.getValue())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断 value 是是否在枚举中
     *
     * @param enumClass 枚举类
     * @param value     枚举值
     * @param <E>       枚举类型
     * @param <V>       值类型
     * @return true：存在
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends KeyValueEnum<K, V>>, K, V> boolean isExistByValue(Class<E> enumClass, V value) {
        for (Enum<? extends KeyValueEnum<K, V>> e : enumClass.getEnumConstants()) {
            if (((KeyValueEnum<K, V>) e).getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据value获取其对应的key
     *
     * @param enums 枚举列表
     * @param value 枚举值
     * @return 枚举名称
     */
    public static <K, V> K getNameByValue(KeyValueEnum<K, V>[] enums, V value) {
        if (value == null) {
            return null;
        }
        for (KeyValueEnum<K, V> e : enums) {
            if (value.equals(e.getValue())) {
                return e.getKey();
            }
        }
        return null;
    }

    /**
     * 根据value获取其对应的key
     *
     * @param enumClass 枚举列表
     * @param value 枚举值
     * @return 枚举名称
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends KeyValueEnum<K, V>>, K, V> K getNameByValue(Class<E> enumClass, V value) {
        KeyValueEnum<K, V> keyValueEnum = (KeyValueEnum<K, V>) getEnumByValue(enumClass, value);
        if (null != keyValueEnum) {
            return keyValueEnum.getKey();
        }
        return null;
    }


    /**
     * 根据key获取其对应的value
     *
     * @param enums 枚举列表
     * @param key  枚举名
     * @return 枚举值
     */
    public static <K, V> V getValueByName(KeyValueEnum<K, V>[] enums, K key) {
        if (key == null) {
            return null;
        }
        for (KeyValueEnum<K, V> e : enums) {
            if (key.equals(e.getKey())) {
                return e.getValue();
            }
        }
        return null;
    }

    /**
     * 根据key获取其对应的value
     *
     * @param enumClass 枚举列表
     * @param key  枚举名
     * @return 枚举值
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends KeyValueEnum<K, V>>, K, V> V getValueByName(Class<E> enumClass, K key) {
        KeyValueEnum<K, V> keyValueEnum = (KeyValueEnum<K, V>) getEnumByName(enumClass, key);
        if (null != keyValueEnum) {
            return keyValueEnum.getValue();
        }
        return null;
    }


    /**
     * 根据key获取对应的枚举对象
     *
     * @param enums 枚举列表
     * @return 枚举对象
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends KeyValueEnum<K, V>>, K, V> E getEnumByName(E[] enums, K key) {
        for (E e : enums) {
            if (((KeyValueEnum<K, V>) e).getKey().equals(key)) {
                return e;
            }
        }
        return null;
    }


    /**
     * 根据key获取对应的枚举对象
     *
     * @param enumClass 枚举class
     * @return 枚举对象
     */
    public static <E extends Enum<? extends KeyValueEnum<K, V>>, K, V> E getEnumByName(Class<E> enumClass, K key) {
        return getEnumByName(enumClass.getEnumConstants(), key);
    }

    /**
     * 根据value获取对应的枚举对象
     *
     * @param enums 枚举列表
     * @return 枚举对象
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends KeyValueEnum<K, V>>, K, V> E getEnumByValue(E[] enums, V value) {
        for (E e : enums) {
            if (((KeyValueEnum<K, V>) e).getValue().equals(value)) {
                return e;
            }
        }
        return null;
    }


    /**
     * 根据value获取对应的枚举对象
     *
     * @param enumClass 枚举class
     * @return 枚举对象
     */
    public static <E extends Enum<? extends KeyValueEnum<K, V>>, K, V> E getEnumByValue(Class<E> enumClass, V value) {
        return getEnumByValue(enumClass.getEnumConstants(), value);
    }

}
