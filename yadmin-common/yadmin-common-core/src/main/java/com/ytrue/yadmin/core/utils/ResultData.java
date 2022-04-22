package com.ytrue.yadmin.core.utils;

import com.ytrue.yadmin.core.base.BaseExceptionCode;
import com.ytrue.yadmin.core.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ytrue
 * @date 2021/2/28 12:47
 * @description 统一返回
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultData<T> {


    private Integer code;


    private String message;


    private T data;

    public ResultData(T data) {
        this.data = data;
    }


    /**
     * 成功返回 默认返回
     *
     * @param <T>
     * @return
     */
    public static <T> ResultData<T> success() {
        ResultData<T> resp = new ResultData<>(null);
        //操作成功
        resp.setCode(ResponseCode.SUCCESS.getCode());
        resp.setMessage(ResponseCode.SUCCESS.getMessage());
        return resp;
    }

    /**
     * 成功返回，自定义code和message
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultData<T> success(T data) {
        ResultData<T> resp = new ResultData<>(data);
        //操作成功
        resp.setCode(ResponseCode.SUCCESS.getCode());
        resp.setMessage(ResponseCode.SUCCESS.getMessage());
        return resp;
    }


    /**
     * 失败返回，默认
     *
     * @param <T>
     * @return
     */
    public static <T> ResultData<T> fail() {
        ResultData<T> resp = new ResultData<>(null);
        //操作失败
        resp.setCode(ResponseCode.FAIL.getCode());
        resp.setMessage(ResponseCode.FAIL.getMessage());
        return resp;
    }

    /**
     * 失败返回,自定义 baseExceptionCode
     *
     * @param baseExceptionCode
     * @param <T>
     * @return
     */
    public static <T> ResultData<T> fail(BaseExceptionCode baseExceptionCode) {
        ResultData<T> resp = new ResultData<>();
        //操作失败
        resp.setCode(baseExceptionCode.getCode());
        resp.setMessage(baseExceptionCode.getMessage());
        return resp;
    }

    /**
     * 失败返回,自定义 message,code
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResultData<T> fail(Integer code, String message) {
        ResultData<T> resp = new ResultData<>();
        //操作失败
        resp.setCode(code);
        resp.setMessage(message);
        return resp;
    }


    /**
     * 自定义code, message, data
     *
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultData<T> returnJson(Integer code, String message, T data) {
        ResultData<T> resp = new ResultData<>(data);
        //操作成功
        resp.setCode(code);
        resp.setMessage(message);
        return resp;
    }
}
