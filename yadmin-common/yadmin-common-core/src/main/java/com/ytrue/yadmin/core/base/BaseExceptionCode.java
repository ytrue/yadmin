package com.ytrue.yadmin.core.base;

/**
 * @author ytrue
 * @date 2021/4/19 09:44
 * @description 基础
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
