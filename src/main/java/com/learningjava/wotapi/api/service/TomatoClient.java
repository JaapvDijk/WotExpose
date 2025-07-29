package com.learningjava.wotapi.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningjava.wotapi.api.model.tomato.dto.TomatoTankPerformanceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class TomatoClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RestClient restClient;
    private final RetryTemplate retryTemplate;
    private final ObjectMapper objectMapper;

    public TomatoClient(@Qualifier("tomatoRestClient") RestClient restClient,
                        RetryTemplate retryTemplate,
                        ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.retryTemplate = retryTemplate;
        this.objectMapper = objectMapper;
    }

    public TomatoTankPerformanceResponse getTankPerformance(String region) {
        var root = retryTemplate.execute(
                __ -> restClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .pathSegment("tank-performance", "recent", region + ".json")
                                .queryParam("mode", "recent")
                                .queryParam("server", region)
                                .build())
                        .retrieve()
                        .onStatus(HttpStatusCode::isError, (request, response) -> logger.error("[Tomato Import] Error {} calling {}", response.getStatusCode(), request.getURI()))
                        .body(JsonNode.class),
                e -> {
                    logger.error("All retry attempts failed: {}", e);
                    return null;
                });

        if (root == null || !root.has("pageProps")) {
            throw new IllegalStateException("Response JSON missing 'pageProps' node");
        }

        JsonNode pageProps = root.get("pageProps");
        JsonNode data = pageProps.get("data");
        if (data == null || data.isNull()) {
            throw new IllegalStateException("Response JSON missing 'data' node inside 'pageProps'");
        }

        return objectMapper.convertValue(data, TomatoTankPerformanceResponse.class);
    }
}