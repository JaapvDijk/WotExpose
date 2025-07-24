package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.config.RetryConfig;
import com.learningjava.wotapi.api.model.tomato.dto.TomatoTankPerformanceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class TomatoClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RestClient restClient;
    private final RetryTemplate retryTemplate;

    public TomatoClient(@Qualifier("tomatoRestClient") RestClient restClient, RetryTemplate retryTemplate) {
        this.restClient = restClient;
        this.retryTemplate = retryTemplate;
    }

    public TomatoTankPerformanceResponse getTankPerformance(String region) {
        return retryTemplate.execute(a -> restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment("tank-performance", "recent", region + ".json")
                        .queryParam("mode", "recent")
                        .queryParam("server", region)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    logger.error("[Tomato Import] Error {} calling {}", response.getStatusCode(), request.getURI());
                })
                .body(TomatoTankPerformanceResponse.class));
    }

    @Recover
    public TomatoTankPerformanceResponse recover(RuntimeException e) {
        logger.error("All retry attempts failed: {}", e.getMessage());
        return null;
    }
}