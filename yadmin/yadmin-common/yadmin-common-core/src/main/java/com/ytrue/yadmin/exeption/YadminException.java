package com.ytrue.yadmin.exeption;

/**
 * @author yangyi
 * @date 2021/3/15 13:05
 * @descriptio 自定义异常
 */
public class YadminException extends RuntimeException {


    private static final long serialVersionUID = -3287102249803473517L;

    public YadminException(String message) {
        super(message);
    }
}
