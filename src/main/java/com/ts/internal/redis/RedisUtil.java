package com.ts.internal.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@Configuration
public class RedisUtil<T> {

    private RedisTemplate<String, T> redisTemplate;
    private ValueOperations<String, T> valueOperations;
    private ListOperations<String, T> listOperations;

    @Autowired
    RedisUtil(RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
        this.listOperations = redisTemplate.opsForList();
    }

    public void putValue(String key, T value) {
        valueOperations.set(key, value);
    }

    public T getValue(String key) {
        return valueOperations.get(key);
    }
   
    public List<T> getValues(String key) {
        List<T> values = new ArrayList<>();
        Long size = listOperations.size(key);
        if (null != size) {
            values = listOperations.range(key, 0, size);
            }
        return values;
    }

    public void pushValue(String key, String value) {
        listOperations.rightPush(key, (T) value);
    }

    public void popValue(String key, String value) {
        listOperations.remove(key, 1, (T) value);
    }
    
    public void setExpire(String key){
        redisTemplate.expire(key, RedisConstants.TIME_TO_EXPIRE, TimeUnit.DAYS);
    }
}
