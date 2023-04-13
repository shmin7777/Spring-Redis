package com.example.redis;

import java.util.Date;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.example.redis.model.Meeting;
import com.example.redis.repositoy.MeetingRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DefaultDataPopulator implements ApplicationRunner {

    private final MeetingRepository meetingRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Meeting meeting = new Meeting();
//        meeting.setTitle("new meeting");
//        meeting.setStartAt(new Date());
//        meetingRepository.save(meeting);
//        // @id가 붙은 필드가 null이면 랜덤으로 저장
//        // Meeting:id key값으로 저장됨
//        // Meeting type : set
//        // Meeting:id type : hash
//        
//        meetingRepository.findAll().forEach(m -> {
//            System.out.println("============================");
//            System.out.println(m);
//            System.out.println("============================");
//        });
    }

}
