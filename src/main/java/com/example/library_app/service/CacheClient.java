package com.example.library_app.service;

import java.util.List;
import redis.clients.jedis.JedisPoolConfig;
public interface CacheClient {
    void set (String key,Object value);
    Object get(String key);
    void delete(String key);
    void deleteAll(List<String> keys);
    void shutdown();

    class Builder{

        private String host;
        private String port;
        private boolean failOnError=true;


        public Builder host(String host)
        {
            this.host=host;
            return this;
        }

        public Builder port(String port)
        {
            this.port=port;
            return this;
        }

        public Builder failOnError(boolean failOnError)
        {
            this.failOnError =failOnError;
            return this;
        }

        public CacheClient build()
        {
            return new CacheManager(host,
                    Integer.parseInt(port),
                    failOnError,
                    new JedisPoolConfig());

        }

    }

}
