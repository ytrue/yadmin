package com.ytrue.yadmin.exeption.code;

/**
 * @author ytrue
 * @date 2021/4/19 09:44
 * @description 解除
 */
public interface BaseExceptionCode {
    /**
     * 异常编码
     *
     * @return
     */
    Integer getCode();

    /**
     * 异常消息
     *
     * @return
     */
    String getMessage();
}
