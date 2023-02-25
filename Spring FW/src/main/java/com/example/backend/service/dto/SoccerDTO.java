package com.example.backend.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@ToString
@Getter
@Setter
public class SoccerDTO extends ApplicationEvent {
    private String location;
    private int numberOfPlayers;

    public SoccerDTO(Object source, String location, int numberOfPlayers) {
        super(source);
        this.location = location;
        this.numberOfPlayers = numberOfPlayers;
    }
}
