package com.ytrue.yadmin.common.response;

import com.ytrue.yadmin.common.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ytrue
 * @date 2021/4/21 15:27
 * @description Response工具类
 */
@Slf4j
public final class ResponseUtil {

    /**
     * 发送文本。使用UTF-8编码。
     *
     * @param response HttpServletResponse
     * @param text     发送的字符串
     */
    public static void renderText(HttpServletResponse response, String text) {
        render(response, "text/plain;charset=UTF-8", text);
    }

    /**
     * 发送json。使用UTF-8编码。
     *
     * @param response HttpServletResponse
     * @param object   发送的字符串
     */
    public static void renderJson(HttpServletResponse response, Object object) {

        render(response, "application/json;charset=UTF-8", JsonUtil.toJsonString(object));
    }

    /**
     * 发送xml。使用UTF-8编码。
     *
     * @param response HttpServletResponse
     * @param text     发送的字符串
     */
    public static void renderXml(HttpServletResponse response, String text) {
        render(response, "text/xml;charset=UTF-8", text);
    }

    /**
     * 发送内容。使用UTF-8编码。
     *
     * @param response
     * @param contentType
     * @param text
     */
    public static void render(HttpServletResponse response, String contentType,
                              String text) {
        response.setContentType(contentType);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        try {
            response.getWriter().write(text);
            response.getWriter().flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
