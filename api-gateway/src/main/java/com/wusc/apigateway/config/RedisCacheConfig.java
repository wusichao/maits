package com.wusc.apigateway.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
    @Autowired
    private ObjectMapper objectMapper;
    /**
     * 生成key的策略
     *
     * @return
     */
    @Bean(name = "wrapperKey")
    public KeyGenerator wrapperKey() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    try {
                        sb.append(objectMapper.writeValueAsString(obj));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
                return sb.toString();
            }
        };
    }
    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory factory) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate(factory));
        cacheManager.setDefaultExpiration(300);
        cacheManager.setLoadRemoteCachesOnStartup(true); // 启动时加载远程缓存
        cacheManager.setUsePrefix(true); //是否使用前缀生成器
        // 这里可进行一些配置 包括默认过期时间 每个cacheName的过期时间等 前缀生成等等
        return cacheManager;
    }

    /**
     * RedisTemplate配置
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        Jackson2JsonRedisSerializer<Object> serializer = jackson2JsonRedisSerializer();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(serializer);
        return redisTemplate;
    }
    @Bean
    public Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        final Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }
}