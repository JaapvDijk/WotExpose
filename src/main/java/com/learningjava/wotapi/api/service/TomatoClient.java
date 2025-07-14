package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.model.tomato.dto.TomatoTankPerformanceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class TomatoClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RestClient restClient;

    public TomatoClient(@Qualifier("tomatoRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 10000, multiplier = 5.0))
    public TomatoTankPerformanceResponse getTankPerformance() {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/tank-performance/recent/EU.json")
                        .queryParam("mode", "recent")
                        .queryParam("server", "EU")
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    logger.error("[Tomato Import] Error {} calling {}", response.getStatusCode(), request.getURI());
                })
                .body(TomatoTankPerformanceResponse.class);
    }

    @Recover
    public TomatoTankPerformanceResponse recover(RuntimeException e) {
        logger.error("All retry attempts failed: {}", e.getMessage());
        return null;
    }
}