package com.ytrue.yadmin.modules.security.integration;

/**
 * @author ytrue
 * @date 2021/2/28 12:47
 * @description 集成认证上下文
 */
public class IntegrationAuthenticationContext {
    private static final ThreadLocal<IntegrationAuthenticationEntity> HOLDERS = new ThreadLocal<>();

    public static void set(IntegrationAuthenticationEntity entity) {
        HOLDERS.set(entity);
    }

    public static IntegrationAuthenticationEntity get() {
        return HOLDERS.get();
    }

    public static void clear() {
        HOLDERS.remove();
    }
}
