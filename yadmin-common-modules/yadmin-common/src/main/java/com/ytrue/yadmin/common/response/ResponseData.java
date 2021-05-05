package com.ytrue.yadmin.common.response;

import com.alibaba.fastjson.JSON;
import com.ytrue.yadmin.common.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author ytrue
 * @date 2021/2/28 12:47
 * @description 统一返回
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {

    private Integer code;

    private String message;

    private T data;

    public ResponseData(T data) {
        this.data = data;
    }


    /**
     * 成功返回 默认返回
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> success() {
        ResponseData<T> resp = new ResponseData<>(null);
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
    public static <T> ResponseData<T> success(T data) {
        ResponseData<T> resp = new ResponseData<T>(data);
        //操作成功
        resp.setCode(ResponseCode.SUCCESS.getCode());
        resp.setMessage(ResponseCode.SUCCESS.getMessage());
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
    public static <T> ResponseData<T> success(String message, T data) {
        ResponseData<T> resp = new ResponseData<T>(data);
        //操作成功
        resp.setCode(ResponseCode.SUCCESS.getCode());
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
    public static <T> ResponseData<T> success(Integer code, String message, T data) {
        ResponseData<T> resp = new ResponseData<>(data);
        //操作成功
        resp.setCode(code);
        resp.setMessage(message);
        return resp;
    }

    /**
     * 失败返回，默认
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> fail() {
        ResponseData<T> resp = new ResponseData<>(null);
        //操作失败
        resp.setCode(ResponseCode.EXCEPTION.getCode());
        resp.setMessage(ResponseCode.EXCEPTION.getMessage());
        return resp;
    }

    /**
     * 失败返回，自定义 message
     *
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseData<T> fail(String message) {
        ResponseData<T> resp = new ResponseData<>();
        //操作失败
        resp.setCode(ResponseCode.EXCEPTION.getCode());
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
    public static <T> ResponseData<T> fail(Integer code, String message) {
        ResponseData<T> resp = new ResponseData<>();
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
    public static <T> ResponseData<T> fail(Integer code, String message, T data) {
        ResponseData<T> resp = new ResponseData<>(data);
        //操作失败
        resp.setCode(code);
        resp.setMessage(message);
        return resp;
    }


    /**
     * 通过HttpServletResponse 返回 json
     *
     * @param response
     * @param result
     */
    public static void jsonOut(HttpServletResponse response, ResponseData<Object> result) {
        PrintWriter out = null;
        try {
            //设置200，方便前端处理
            response.setStatus(ResponseCode.SUCCESS.getCode());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JsonUtil.toJsonString(result));
        } catch (Exception e) {
            log.error(e + "输出JSON出错");
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}
