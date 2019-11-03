package com.ts.internal.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@RunWith(MockitoJUnitRunner.class)
public class RedisUtilTest<T> {

    @Mock
    private ValueOperations<String, T> valueOperations;

    @Mock
    private ListOperations<String, T> listOperations;

    private RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();

    @InjectMocks
    private RedisUtil<String> redisUtil = new RedisUtil(redisTemplate);


    @Test
    public void testPushValue() {
        String key = "node_6_descendants";
        String value = "node_7";
        Long rowsInserted = 1l;
        Mockito.when(listOperations.rightPush(key, (T) value)).thenReturn(rowsInserted);
        redisUtil.pushValue(key, value);
    }

    @Test
    public void testPopValue() {
        String key = "node_6_descendants";
        String value = "node_7";
        Long rowsInserted = 1l;
        Mockito.when(listOperations.remove(key, 1, (T) value)).thenReturn(rowsInserted);
        redisUtil.popValue(key, value);
    }

}
