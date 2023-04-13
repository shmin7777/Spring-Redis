package com.example.redis.repositoy;

import org.springframework.data.repository.CrudRepository;
import com.example.redis.model.Meeting;

public interface MeetingRepository extends CrudRepository<Meeting, String> {

}
