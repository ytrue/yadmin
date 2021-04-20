package com.ytrue.yadmin.common.exeption;

/**
 * @author yangyi
 * @date 2021/3/15 13:05
 * @descriptio 自定义异常
 */
public class YadminException extends RuntimeException {


    public YadminException(String message) {
        super(message);
    }
}
