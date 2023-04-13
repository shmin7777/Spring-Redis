package com.example.redis.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class RedisCRUDTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void stringTest() {
        final String key = "a";
        final String data = "1";

        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, data);

        final String s = opsForValue.get(key);

        assertEquals(s, data);

        opsForValue.increment(key);

        assertEquals(opsForValue.get(key), Integer.parseInt(data) + 1);
    }

    @Test
    void objectTest() {

    }
}
