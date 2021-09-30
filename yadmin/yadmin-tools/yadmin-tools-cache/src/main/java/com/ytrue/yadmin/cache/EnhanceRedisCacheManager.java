package com.ytrue.yadmin.cache;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

/**
 * @author ytrue
 * @date 2021/9/30 13:56
 * @description 增强RedisCacheManager
 */
public class EnhanceRedisCacheManager extends RedisCacheManager {

    private final RedisCacheWriter cacheWriter;

    private final RedisCacheConfiguration defaultCacheConfig;

    /**
     * 用于返回自定义的redisCache
     *
     * @param name        名称
     * @param cacheConfig 缓存配置
     * @return
     */
    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        return new EnhanceRedisCache(name, cacheWriter, cacheConfig != null ? cacheConfig : defaultCacheConfig);

    }

    public EnhanceRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
        this.cacheWriter = cacheWriter;
        this.defaultCacheConfig = defaultCacheConfiguration;
    }

}


