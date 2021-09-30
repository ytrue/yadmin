package com.ytrue.yadmin.cache;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author ytrue
 * @date 2021/9/30 14:55
 * @description RedisConfiguration
 */
@Slf4j
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {

    /**
     * 1.项目启动时此方法先被注册成bean被spring管理,
     * 如果没有这个bean，则redis可视化工具中的中文内容（key或者value）都会以二进制存储，不易检查
     * <p>
     * //使用jackson序列化，会加入@class，故不使用此方法
     * Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
     * ObjectMapper objectMapper = new ObjectMapper();
     * objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
     * <p>
     * //Jackson框架enableDefaultTyping方法反序列化漏洞
     * //该漏洞是由于Jackson框架enableDefaultTyping方法存在Java反序列化代码执行漏洞，
     * //攻击者利用漏洞可在服务器主机上执行任意代码或系统指令，取得网站服务器的控制权。漏洞影响范围 Jackson 2.7 < 2.7.10
     * //之前使用 enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL),现在替换如下
     * <p>
     * objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
     * ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
     * jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
     *
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        //使用fast json作为序列化，不会出现@class字段，目前不需要@class，故使用fast json作为序列化
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用json
        template.setValueSerializer(fastJsonRedisSerializer);
        // hash的value序列化方式采用json
        template.setHashValueSerializer(fastJsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 自定义缓存管理器
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        // 配置序列化,转成json格式
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(redisTemplate.getValueSerializer()));

        return new EnhanceRedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }
}
