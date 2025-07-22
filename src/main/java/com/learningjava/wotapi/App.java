package com.learningjava.wotapi;

import com.learningjava.wotapi.api.config.ApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableConfigurationProperties(ApiProperties.class)
@EnableScheduling
@EnableRetry
public class App {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);

        //CHECK: https://blog.tericcabrel.com/handle-database-migrations-in-a-springboot-application-with-flyway/
    }
}
