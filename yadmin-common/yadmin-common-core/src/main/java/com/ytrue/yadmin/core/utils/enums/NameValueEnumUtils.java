package com.ytrue.yadmin.core.utils.enums;

/**
 * @author ytrue
 * @date 2022/5/28 19:17
 * @description 枚举常用工具类。<br/>
 * 使用该枚举工具类需要指定的枚举实现
 */
public final class NameValueEnumUtils {

    /**
     * 判断name 是是否在枚举中
     *
     * @param enums 枚举数组
     * @param name  枚举值
     * @return 是否存在
     */
    public static <N, V> boolean isExistByName(NameValueEnum<N, V>[] enums, N name) {
        if (name == null) {
            return false;
        }
        for (NameValueEnum<N, V> e : enums) {
            if (name.equals(e.getName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断name 是是否在枚举中
     *
     * @param enumClass 枚举类
     * @param name      枚举值
     * @param <E>       枚举类型
     * @param <N>       值类型
     * @return true：存在
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends NameValueEnum<N, V>>, N, V> boolean isExistByName(Class<E> enumClass, N name) {
        for (Enum<? extends NameValueEnum<N, V>> e : enumClass.getEnumConstants()) {
            if (((NameValueEnum<N, V>) e).getName().equals(name)) {
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
    public static <N, V> boolean isExistByValue(NameValueEnum<N, V>[] enums, V value) {
        if (value == null) {
            return false;
        }
        for (NameValueEnum<N, V> e : enums) {
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
    public static <E extends Enum<? extends NameValueEnum<N, V>>, N, V> boolean isExistByValue(Class<E> enumClass, V value) {
        for (Enum<? extends NameValueEnum<N, V>> e : enumClass.getEnumConstants()) {
            if (((NameValueEnum<N, V>) e).getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据value获取其对应的name
     *
     * @param enums 枚举列表
     * @param value 枚举值
     * @return 枚举名称
     */
    public static <N, V> N getNameByValue(NameValueEnum<N, V>[] enums, V value) {
        if (value == null) {
            return null;
        }
        for (NameValueEnum<N, V> e : enums) {
            if (value.equals(e.getValue())) {
                return e.getName();
            }
        }
        return null;
    }

    /**
     * 根据value获取其对应的name
     *
     * @param enumClass 枚举列表
     * @param value 枚举值
     * @return 枚举名称
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends NameValueEnum<N, V>>, N, V> N getNameByValue(Class<E> enumClass, V value) {
        NameValueEnum<N, V> nameValueEnum = (NameValueEnum<N, V>) getEnumByValue(enumClass, value);
        if (null != nameValueEnum) {
            return nameValueEnum.getName();
        }
        return null;
    }


    /**
     * 根据name获取其对应的value
     *
     * @param enums 枚举列表
     * @param name  枚举名
     * @return 枚举值
     */
    public static <N, V> V getValueByName(NameValueEnum<N, V>[] enums, N name) {
        if (name == null) {
            return null;
        }
        for (NameValueEnum<N, V> e : enums) {
            if (name.equals(e.getName())) {
                return e.getValue();
            }
        }
        return null;
    }

    /**
     * 根据name获取其对应的value
     *
     * @param enumClass 枚举列表
     * @param name  枚举名
     * @return 枚举值
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends NameValueEnum<N, V>>, N, V> V getValueByName(Class<E> enumClass, N name) {
        NameValueEnum<N, V> nameValueEnum = (NameValueEnum<N, V>) getEnumByName(enumClass, name);
        if (null != nameValueEnum) {
            return nameValueEnum.getValue();
        }
        return null;
    }


    /**
     * 根据name获取对应的枚举对象
     *
     * @param enums 枚举列表
     * @return 枚举对象
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends NameValueEnum<N, V>>, N, V> E getEnumByName(E[] enums, N name) {
        for (E e : enums) {
            if (((NameValueEnum<N, V>) e).getName().equals(name)) {
                return e;
            }
        }
        return null;
    }


    /**
     * 根据name获取对应的枚举对象
     *
     * @param enumClass 枚举class
     * @return 枚举对象
     */
    public static <E extends Enum<? extends NameValueEnum<N, V>>, N, V> E getEnumByName(Class<E> enumClass, N name) {
        return getEnumByName(enumClass.getEnumConstants(), name);
    }

    /**
     * 根据value获取对应的枚举对象
     *
     * @param enums 枚举列表
     * @return 枚举对象
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends NameValueEnum<N, V>>, N, V> E getEnumByValue(E[] enums, V value) {
        for (E e : enums) {
            if (((NameValueEnum<N, V>) e).getValue().equals(value)) {
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
    public static <E extends Enum<? extends NameValueEnum<N, V>>, N, V> E getEnumByValue(Class<E> enumClass, V value) {
        return getEnumByValue(enumClass.getEnumConstants(), value);
    }

}
