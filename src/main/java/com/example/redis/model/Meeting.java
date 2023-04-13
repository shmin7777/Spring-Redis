package com.example.redis.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import lombok.Data;

// @RedisHash: Redis 에 저장할 자료구조인 객체를 정의합니다
// value : Redis 의 keyspace 값
// ttl : 만료기간 second 단위, default : 만료기간 없음(-1)
@RedisHash(value="Meeting", timeToLive = 5)
@Data
public class Meeting {
    // @Id 어노테이션이 붙은 필드가 Redis Key 값이 되며 null 로 세팅하면 랜덤값이 설정됩니다
    // keyspace 와 합쳐져서 레디스에 저장된 최종 키 값은 keyspace:id 가 됩니다.
    @Id
    private String id;
    private String title;
    private Date startAt;

}
