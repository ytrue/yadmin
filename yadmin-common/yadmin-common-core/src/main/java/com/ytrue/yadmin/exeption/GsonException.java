package com.ytrue.yadmin.exeption;

/**
 * @author ytrue
 * @date 2021/6/13 14:58
 * @description GsonException
 */
public class GsonException extends RuntimeException {

    public GsonException() {
    }

    public GsonException(String message) {
        super(message);
    }

    public GsonException(String message, Throwable cause) {
        super(message, cause);
    }
}
