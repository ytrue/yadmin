package com.ytrue.yadmin.exeption;

/**
 * @author ytrue
 * @date 2021/4/21 16:53
 * @description 自动验证不通过异常
 */
public class AutoValidException extends RuntimeException {


    public AutoValidException(String message) {
        super(message);
    }

}
