package com.ytrue.yadmin.modules.security.integration;


import com.ytrue.yadmin.modules.security.integration.authenticator.IntegrationAuthenticator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/**
 * @author ytrue
 * @date 2021/2/28 12:47
 * @description 集成认证拦截器
 */
@Component
public class IntegrationAuthenticationFilter extends GenericFilterBean implements ApplicationContextAware {
    /**
     * 登录类型参数名
     */
    private static final String AUTH_TYPE_PARM_NAME = "auth_type";


    private final String PASSWORD = "password";

    /**
     * 需要拦截的路由
     */
    private static final String OAUTH_TOKEN_URL = "/oauth/token";

    private final RequestMatcher requestMatcher;

    private ApplicationContext applicationContext;

    private Collection<IntegrationAuthenticator> authenticators;

    public IntegrationAuthenticationFilter() {
        this.requestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, "GET"),
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, "POST")
        );
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (requestMatcher.matches(request)) {
            RequestParameterWrapper requestParameterWrapper = new RequestParameterWrapper(request);
            if (requestParameterWrapper.getParameter(PASSWORD) == null) {
                requestParameterWrapper.addParameter(PASSWORD, "");
            }
            IntegrationAuthenticationEntity entity = new IntegrationAuthenticationEntity();
            entity.setAuthType(requestParameterWrapper.getParameter(AUTH_TYPE_PARM_NAME));
            entity.setAuthParameters(requestParameterWrapper.getParameterMap());
            IntegrationAuthenticationContext.set(entity);
            try {
                this.prepare(entity);
                filterChain.doFilter(requestParameterWrapper, servletResponse);
                this.complete(entity);
            } finally {
                IntegrationAuthenticationContext.clear();
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 认证前回调
     *
     * @param entity 集成认证实体
     */
    private void prepare(IntegrationAuthenticationEntity entity) {
        if (entity != null) {
            synchronized (this) {
                Map<String, IntegrationAuthenticator> map = applicationContext.getBeansOfType(IntegrationAuthenticator.class);
                if (map != null) {
                    this.authenticators = map.values();
                }
            }
        }
        if (this.authenticators == null) {
            this.authenticators = new ArrayList<>();
        }
        for (IntegrationAuthenticator authenticator : this.authenticators) {
            if (authenticator.support(entity)) {
                authenticator.prepare(entity);
            }
        }
    }

    /**
     * 认证结束后回调
     *
     * @param entity 集成认证实体
     */
    private void complete(IntegrationAuthenticationEntity entity) {
        for (IntegrationAuthenticator authenticator : authenticators) {
            if (authenticator.support(entity)) {
                authenticator.complete(entity);
            }
        }
    }

    /**
     * 用途：在拦截时给Request添加参数
     * Cloud OAuth2 密码模式需要判断Request是否存在password参数，
     * 如果不存在会抛异常结束认证
     * 所以在调用doFilter方法前添加password参数
     */
    static class RequestParameterWrapper extends HttpServletRequestWrapper {
        private final Map<String, String[]> params = new HashMap<String, String[]>();

        public RequestParameterWrapper(HttpServletRequest request) {
            super(request);
            this.params.putAll(request.getParameterMap());
        }

        public RequestParameterWrapper(HttpServletRequest request, Map<String, Object> extraParams) {
            this(request);
            addParameters(extraParams);
        }

        public void addParameters(Map<String, Object> extraParams) {
            for (Map.Entry<String, Object> entry : extraParams.entrySet()) {
                addParameter(entry.getKey(), entry.getValue());
            }
        }

        @Override
        public String getParameter(String name) {
            String[] values = params.get(name);
            if (values == null || values.length == 0) {
                return null;
            }
            return values[0];
        }

        @Override
        public String[] getParameterValues(String name) {
            return params.get(name);
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return params;
        }

        public void addParameter(String name, Object value) {
            if (value != null) {
                if (value instanceof String[]) {
                    params.put(name, (String[]) value);
                } else if (value instanceof String) {
                    params.put(name, new String[]{(String) value});
                } else {
                    params.put(name, new String[]{String.valueOf(value)});
                }
            }
        }

    }
}
