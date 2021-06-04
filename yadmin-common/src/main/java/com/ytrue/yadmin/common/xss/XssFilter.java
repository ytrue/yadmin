package com.ytrue.yadmin.common.xss;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterConfig;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author ytrue
 * @date 2021/2/28 12:47
 * @description xss一些简单的安全过滤：
 */
public class XssFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;


        logger.info("XssFilter-uri:{}", req.getRequestURI());
        // xss 过滤
        chain.doFilter(new XssWrapper(req), resp);
    }

    @Override
    public void destroy() {

    }
}
