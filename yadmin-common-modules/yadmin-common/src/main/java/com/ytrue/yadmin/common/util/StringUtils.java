package com.ytrue.yadmin.common.util;

import java.util.HashMap;

/**
 * @author ytrue
 * @description 自定义字符串工具库类
 */
public class StringUtils {

    /**
     * 对比两个值的大小,返回字符串拼接
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String minmaxValue(Integer num1, Integer num2) {
        StringBuffer stringBuffer = new StringBuffer();
        HashMap<String, Integer> map = new HashMap<>(16);
        map.put("min", num1);
        map.put("max", num2);
        if (num1 > num2) {
            map.put("min", num2);
            map.put("max", num1);
        }
        stringBuffer.append(map.get("min"));
        stringBuffer.append("-");
        stringBuffer.append(map.get("max"));
        return String.valueOf(stringBuffer);
    }
}
