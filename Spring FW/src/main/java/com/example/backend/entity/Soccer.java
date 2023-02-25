package com.example.backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@ToString
@Getter
@Setter
public class Soccer extends ApplicationEvent {
    private String location;
    private int numberOfPlayers;

    public Soccer(Object source, String location, int numberOfPlayers) {
        super(source);
        this.location = location;
        this.numberOfPlayers = numberOfPlayers;
    }
}
