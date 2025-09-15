package com.learningjava.wotapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
public class App {

    public static void main(String[] args) {
        var context = SpringApplication.run(App.class, args);

        System.out.println("WotExpose app started");
        //CHECK:https://blog.tericcabrel.com/handle-database-migrations-in-a-springboot-application-with-flyway/
    }
}
