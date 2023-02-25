package com.example.backend.config;

import com.example.backend.entity.Symptoms;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@PropertySources({
        @PropertySource("classpath:application-employee.yml"),
})
@ConfigurationProperties(prefix = "anh")
public class Employee {

    private String name;
    private String add;

    @Value("${period:not found value}")
    private String period;

    private Symptoms symptoms;
}


