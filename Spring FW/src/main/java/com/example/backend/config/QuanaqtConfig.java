package com.example.backend.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "quanaqt", ignoreInvalidFields = true, ignoreUnknownFields = true)
public class QuanaqtConfig {

    @Value("${server.port:100}")
    int requireValue;
    private String name;
    private int age;
    private String major;

    @Value("${period:not found value}")
    private String period;

    public QuanaqtConfig() {
        System.out.println(this.getClass().getName()
                + " created because quanaqt.isSent = true and quanzip.enable= true");
    }

}
