package com.ytrue.yadmin.utils;

import com.ytrue.yadmin.exeption.code.BaseExceptionCode;
import com.ytrue.yadmin.exeption.code.ExceptionCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "统一返回")
public class R<T> {

    @ApiModelProperty(value = "状态", example = "200")
    private Integer code;

    @ApiModelProperty(value = "信息", example = "成功")
    private String message;

    @ApiModelProperty(value = "内容")
    private T data;

    public R(T data) {
        this.data = data;
    }


    /**
     * 成功返回 默认返回
     *
     * @param <T>
     * @return
     */
    public static <T> R<T> success() {
        R<T> resp = new R<>(null);
        //操作成功
        resp.setCode(ExceptionCode.SUCCESS.getCode());
        resp.setMessage(ExceptionCode.SUCCESS.getMessage());
        return resp;
    }

    /**
     * 成功返回，自定义code和message
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> success(T data) {
        R<T> resp = new R<T>(data);
        //操作成功
        resp.setCode(ExceptionCode.SUCCESS.getCode());
        resp.setMessage(ExceptionCode.SUCCESS.getMessage());
        return resp;
    }

    /**
     * 成功返回，自定义 message和 data
     *
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> success(String message, T data) {
        R<T> resp = new R<T>(data);
        //操作成功
        resp.setCode(ExceptionCode.SUCCESS.getCode());
        resp.setMessage(message);
        return resp;
    }

    /**
     * 成功返回，自定义code, message, data
     *
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> success(Integer code, String message, T data) {
        R<T> resp = new R<>(data);
        //操作成功
        resp.setCode(code);
        resp.setMessage(message);
        return resp;
    }

    /**
     * 成功返回，自定义 BaseExceptionCode
     *
     * @param baseExceptionCode
     * @param <T>
     * @return
     */
    public static <T> R<T> success(BaseExceptionCode baseExceptionCode) {
        R<T> resp = new R<>(null);
        //操作成功
        resp.setCode(baseExceptionCode.getCode());
        resp.setMessage(baseExceptionCode.getMessage());
        return resp;
    }

    /**
     * 成功返回，BaseExceptionCode, data
     *
     * @param baseExceptionCode
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> success(BaseExceptionCode baseExceptionCode, T data) {
        R<T> resp = new R<>(data);
        //操作成功
        resp.setCode(baseExceptionCode.getCode());
        resp.setMessage(baseExceptionCode.getMessage());
        return resp;
    }

    /**
     * 失败返回，默认
     *
     * @param <T>
     * @return
     */
    public static <T> R<T> fail() {
        R<T> resp = new R<>(null);
        //操作失败
        resp.setCode(ExceptionCode.EXCEPTION.getCode());
        resp.setMessage(ExceptionCode.EXCEPTION.getMessage());
        return resp;
    }

    /**
     * 失败返回，自定义 message
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> R<T> fail(String message) {
        R<T> resp = new R<>();
        //操作失败
        resp.setCode(ExceptionCode.EXCEPTION.getCode());
        resp.setMessage(message);
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
    public static <T> R<T> fail(Integer code, String message) {
        R<T> resp = new R<>();
        //操作失败
        resp.setCode(code);
        resp.setMessage(message);
        return resp;
    }

    /**
     * 失败返回,自定义 message,code,data
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> R<T> fail(Integer code, String message, T data) {
        R<T> resp = new R<>(data);
        //操作失败
        resp.setCode(code);
        resp.setMessage(message);
        return resp;
    }


    /**
     * 失败返回,自定义 baseExceptionCode
     *
     * @param baseExceptionCode
     * @param <T>
     * @return
     */
    public static <T> R<T> fail(BaseExceptionCode baseExceptionCode) {
        R<T> resp = new R<>();
        //操作失败
        resp.setCode(baseExceptionCode.getCode());
        resp.setMessage(baseExceptionCode.getMessage());
        return resp;
    }

    /**
     * 失败返回,自定义 baseExceptionCode,data
     *
     * @param baseExceptionCode
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> fail(BaseExceptionCode baseExceptionCode, T data) {
        R<T> resp = new R<>(data);
        //操作失败
        resp.setCode(baseExceptionCode.getCode());
        resp.setMessage(baseExceptionCode.getMessage());
        return resp;
    }

}
