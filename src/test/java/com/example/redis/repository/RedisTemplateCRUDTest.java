package com.example.redis.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.time.Duration;
import java.util.Date;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import com.example.redis.model.Meeting;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class RedisTemplateCRUDTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testString() {
        final String key = "a";
        final String data = "1";

        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, data);

        final String s = (String) opsForValue.get(key);

        assertEquals(s, data);

        opsForValue.increment(key);

        assertEquals(opsForValue.get(key), "2");
    }

    @Test
    void TestList() {
        Meeting meeting = Meeting.builder()
                .title("object test")
                .startAt(new Date())
                .build();
        
        ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
        
        Set<String> keys = redisTemplate.keys("*");
        keys.forEach(k->log.info("key :: {} ", k));
        

    }
}
