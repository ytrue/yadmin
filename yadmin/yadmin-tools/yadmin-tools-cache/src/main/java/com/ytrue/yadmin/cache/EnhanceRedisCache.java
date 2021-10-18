package com.ytrue.yadmin.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author ytrue
 * @date 2021/9/30 13:55
 * @description 增强EnhanceRedisCache可添加过期时间
 */
@Slf4j
public class EnhanceRedisCache extends RedisCache {

    private final RedisCacheWriter redisCacheWriter;

    private final RedisCacheConfiguration configuration;


    /**
     * 校验规则：获取时间
     */
    private static final String REGEX_STR = ".*#\\d+$";

    /**
     * 分割符
     */
    private static final String Splitter = "#";

    /**
     * Create new {@link RedisCache}.
     *
     * @param name        must not be {@literal null}.
     * @param cacheWriter must not be {@literal null}.
     * @param cacheConfig must not be {@literal null}.
     */
    protected EnhanceRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig) {
        super(name, cacheWriter, cacheConfig);
        redisCacheWriter = cacheWriter;
        configuration = cacheConfig;
    }


    /**
     * 重写cache put 逻辑，引入自定义TTL 实现
     * 实现逻辑:
     * 1.通过获取@Cacheable 中的value ,然后根据约定好的特殊字符进行分割
     * 2.从分割结果集中获取设置的TTL 时间并将value 中的，然后给当前缓存设置TTL
     *
     * @param key   key
     * @param value value
     */
    @Override
    public void put(Object key, Object value) {
        String name = super.getName();
        //是否按照指定的格式
        if (Pattern.matches(REGEX_STR, name)) {
            List<String> keyList = Arrays.asList(name.split(Splitter));
            //获取键值
            String finalName = keyList.get(0);
            //获取TTL 执行时间
            long ttl = Long.parseLong(keyList.get(1));
            //获取缓存value
            Object cacheValue = preProcessCacheValue(value);
            //获取value 为null 时，抛出异常
            if (!isAllowNullValues() && cacheValue == null) {
                throw new IllegalArgumentException(String.format(
                        "Cache '%s' does not allow 'null' values. Avoid storing null via '@Cacheable(unless=\"#result == null\")' or configure RedisCache to allow 'null' via RedisCacheConfiguration.",
                        name));
            }
            //插入时添加时间
            assert cacheValue != null;
            redisCacheWriter.put(finalName, serializeCacheKey(createCacheKey(key)), serializeCacheValue(cacheValue), Duration.ofSeconds(ttl));
        } else {
            //原来逻辑处理
            super.put(key, value);
        }
    }




    /**
     * 描述 现有key 值格式为  key#ttl ;改方法将key 值后边的#ttl 去掉 ；例如test# 10；改方法处理后为test
     *
     * @param key
     * @return
     */
    @Override
    protected String createCacheKey(Object key) {
        return !configuration.usePrefix() ? convertKey(key) : prefixCacheKey(convertKey(key));
    }


    private String prefixCacheKey(String key) {
        String name = super.getName();
        if (Pattern.matches(REGEX_STR, name)) {
            List<String> keyList = Arrays.asList(name.split(Splitter));
            String finalName = keyList.get(0);
            return configuration.getKeyPrefixFor(finalName) + key;
        }
        return configuration.getKeyPrefixFor(name) + key;
    }


}
