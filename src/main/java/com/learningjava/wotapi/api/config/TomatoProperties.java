package com.learningjava.wotapi.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api.tomato")
public class TomatoProperties {

    private String baseUrl;
    private String scheduleExpression;
    private final Client client = new Client();

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getScheduleExpression() {
        return scheduleExpression;
    }

    public void setScheduleExpression(String scheduleExpression) {
        this.scheduleExpression = scheduleExpression;
    }

    public Client getClient() {
        return client;
    }

    public static class Client {
        private final Retry retry = new Retry();

        public Retry getRetry() {
            return retry;
        }

        public static class Retry {
            private int maxAttempts;
            private long delay;
            private double multiplier;

            public int getMaxAttempts() {
                return maxAttempts;
            }

            public void setMaxAttempts(int maxAttempts) {
                this.maxAttempts = maxAttempts;
            }

            public long getDelay() {
                return delay;
            }

            public void setDelay(long delay) {
                this.delay = delay;
            }

            public double getMultiplier() {
                return multiplier;
            }

            public void setMultiplier(double multiplier) {
                this.multiplier = multiplier;
            }
        }
    }
}
