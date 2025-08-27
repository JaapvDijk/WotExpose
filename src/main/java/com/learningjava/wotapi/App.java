package com.learningjava.wotapi;

import com.learningjava.wotapi.infrastructure.DbSeeder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
public class App {

    public static void main(String[] args) {
        var context = SpringApplication.run(App.class, args);

        var dbSeeder = context.getBean(DbSeeder.class);
        dbSeeder.init();

        //CHECK: https://blog.tericcabrel.com/handle-database-migrations-in-a-springboot-application-with-flyway/
    }
}
