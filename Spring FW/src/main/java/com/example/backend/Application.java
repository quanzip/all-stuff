package com.example.backend;

import com.example.backend.config.Employee;
import com.example.backend.config.QuanaqtConfig;
import com.example.backend.config.QuanzipConfig;
import com.example.backend.config.TestCOnfig;
import com.example.backend.entity.Soccer;
import com.example.backend.service.event_publisher.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.StandardEnvironment;

import java.time.LocalDateTime;

@SpringBootApplication
// if in class: QuanaqtConfig there are no @Configuration for spring to knows this is a bean to create
// then we must add following line
//@EnableConfigurationProperties({QuanaqtConfig.class, QuanzipConfig.class})
public class Application implements CommandLineRunner {

    @Value(value = "${spring.subconfig:anh}")
    public final String configBase = "";

    @Autowired
    @Qualifier(value = "getConfigAnh")
    private TestCOnfig appConfig;

    @Autowired
    @Qualifier(value = "getConfigEm")
    private TestCOnfig configEm;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);

        StandardEnvironment environment = new StandardEnvironment();
        environment.setActiveProfiles("dev");

        springApplication.setEnvironment(environment);
        ConfigurableApplicationContext context = springApplication.run(args);
        QuanaqtConfig quanaqtConfig = context.getBean(QuanaqtConfig.class);
        QuanzipConfig quanzipConfig = context.getBean(QuanzipConfig.class);
        Employee employee = context.getBean(Employee.class);

        System.out.println(quanaqtConfig);
        System.out.println(quanzipConfig);
        System.out.println(employee);

        Publisher publisher = context.getBean(Publisher.class);
        publisher.goFishingPublisher();

        Soccer soccer = new Soccer(Application.class, "My Dinh, Ha Noi, Viet Nam", 25);
        publisher.playSoccerPublisher(soccer, LocalDateTime.now());

    }

    @Override
    public void run(String... args) {
        System.out.println("test config Anh: " + appConfig);
        System.out.println("test config Em: " + configEm);
    }
}
