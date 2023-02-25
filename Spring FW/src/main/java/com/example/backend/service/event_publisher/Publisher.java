package com.example.backend.service.event_publisher;

import com.example.backend.entity.GoFishingEvt;
import com.example.backend.entity.Soccer;
import com.example.backend.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class Publisher {

    private final PeopleRepository peopleRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void goFishingPublisher() {
        System.out.println("\n******" + "publisher: Asking anyone go fishing...");
        applicationEventPublisher.publishEvent(new GoFishingEvt(this, 5, "Quanaqt"));
    }

    public void playSoccerPublisher(Soccer soccer, LocalDateTime localDateTime) {
        System.out.println("\n******" + "publisher: Asking anyone go to play soccer at " + localDateTime);
        applicationEventPublisher.publishEvent(soccer);
    }
}
