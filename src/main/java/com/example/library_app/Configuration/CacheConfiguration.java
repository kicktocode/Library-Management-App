package com.example.library_app.Configuration;

import com.example.library_app.service.CacheClient;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfiguration implements CachingConfigurer {
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }
    @Bean(destroyMethod = "shutdown")
    public CacheClient cacheClient()
    {
        String type="redis";
        String host="localhost";
        String port="6379";

        return new CacheClient.Builder()
                .type(type)
                .host(host)
                .port(port)
                .failOnError(false)
                .build();
    }
}
