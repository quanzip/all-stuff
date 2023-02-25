package com.example.backend.config;

import com.example.backend.service.ConditionOnEnv;
import com.example.backend.service.condition.ConditionalOnSystem;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class AppConfig {
    @Bean
    @Conditional(ConditionalOnSystem.class)
    // create this bean when app is running with java version 8
    @ConditionalOnJava(JavaVersion.EIGHT)
    // create this bean when  quanaqt.isSent = true và quanzip.enable = true
    // Every thing in  {} must be compare to true: dont use: ${quanaqt.isSent:FALSE} and ${quanzip.enable:true}
    @ConditionalOnExpression("${quanaqt.isSent:true} and ${quanzip.enable:true}")
    public QuanaqtConfig getAqtConfig() {
        return new QuanaqtConfig();
    }

    @Bean
    // Custom Conditional
    @ConditionOnEnv
    // create this bean when QuanzipConfig did not get created yet
    @ConditionalOnMissingBean
    // create this class when QuanzipConfig did not get created yet
    @ConditionalOnMissingClass
    public QuanzipConfig getZipConfig() {
        return new QuanzipConfig();
    }

    @Bean
    public OpenAPI getOpenApi() {
        return new OpenAPI()
                .servers(new ArrayList<Server>(Arrays.asList(
                        new Server().url("http://localhost:8084/api-ui")
                )))
                .info(new Info()
                        .title("Hêllo from openAPI 3.0")
                        .description("I am working with open API 3.0")
                        .version("2.3.3")
                        .contact(new Contact()
                                .email("quanaqtzip4@gmail.com")
                                .name("Pham Hong Quan")
                                .url("https://mail.viettel.com.vn/#1"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.sachhayonline.com/tua-sach/giai-thich-thanh-ngu-tuc-ngu/deo-cay-giua-duong/1927#:~:text=C%C3%A2u%20th%C3%A0nh%20ng%E1%BB%AF%20n%C3%A0y%20h%C3%A0m,ng%E1%BB%93i%20tr%C3%AAn%20%C4%91%C6%B0%E1%BB%9Dng%20%C4%91%E1%BA%BDo%20c%C3%A0y."))
                        .version("1.1"));
    }

    @Bean
    @ConfigurationProperties(prefix = "em", ignoreInvalidFields = true)
    public TestCOnfig getConfigEm(){
        return new TestCOnfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "anh")
    public TestCOnfig getConfigAnh(){
        return new TestCOnfig();
    }
}
