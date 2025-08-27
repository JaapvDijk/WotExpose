package com.learningjava.wotapi.application.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationProperties(prefix = "api.tomato")
public class TomatoProperties {

    @Setter
    private String baseUrl;
    @Setter
    private String scheduleExpression;
    private final Client client = new Client();

    @Getter
    public static class Client {
        private final Retry retry = new Retry();

        @Setter
        @Getter
        public static class Retry {
            private int maxAttempts;
            private long delay;
            private double multiplier;

        }
    }
}
