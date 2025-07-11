package com.learningjava.wotapi.service;

import com.learningjava.wotapi.api.model.Tomato.TomatoTankPerformance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
public class TomatoClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RestClient restClient;

    public TomatoClient(@Qualifier("tomatoRestClient") RestClient restClient) {
        this.restClient = restClient;
    }

    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 1, multiplier = 2.0))
    public Optional<TomatoTankPerformance> getTankPerformance() { //TODO: dont use block? implement retry
        TomatoTankPerformance tankPerformanceEU;
        try {
            tankPerformanceEU = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/tank-peformance/recent/EU.json")
                            .queryParam("mode", "recent")
                            .queryParam("server", "EU")
                            .build())
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, (request, response) -> {
                        logger.warn("[Tomato Import] Error {} calling {}", response.getStatusCode(), request.getURI());
                    })
                    .body(TomatoTankPerformance.class);
        }
        catch (Exception e) {
            logger.info("[Tomato Import] Failed", e);
            return Optional.empty();
        }

        return tankPerformanceEU;
    }
}