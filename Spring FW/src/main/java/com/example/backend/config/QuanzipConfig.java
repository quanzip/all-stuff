package com.example.backend.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "quanzip", ignoreInvalidFields = true, ignoreUnknownFields = true)
public class QuanzipConfig {
    private String name;
    private int age;
    private String major;
    @Value("${period:not found value}")
    private String period;

    public QuanzipConfig() {
        System.out.println(this.getClass().getName() + " created because it was not created befored");
    }
}
