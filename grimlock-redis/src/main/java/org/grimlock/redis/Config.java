package org.grimlock.redis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

@Configuration
public class Config extends CachingConfigurerSupport {
    /**
     * redis生成规则
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            public Object generate(Object target, Method method,
                                   Object... params) {
                String key = target.getClass().getName() + "$"
                        + method.getName() + "$" + params;
                return key;
            }
        };
    }

    /**
     * 缓存管理
     * @param redisTemplate
     * @param expirationDate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate,int expirationDate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);

        if(expirationDate!=0)
        {
            //设置缓存过期时间
            rcm.setDefaultExpiration(60);//秒
        }
        return rcm;
    }


}
