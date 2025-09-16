package com.learningjava.wotexpose.infrastructure.client.tomato;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningjava.wotexpose.infrastructure.client.tomato.dto.TomatoTankPerformancesResponse;
import com.learningjava.wotexpose.shared.constant.RegionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class TomatoClient {

    private final Logger logger = LoggerFactory.getLogger(TomatoClient.class);
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

    public TomatoTankPerformancesResponse getTankPerformance(RegionType region) {
        try {
            JsonNode root = retryTemplate.execute(
                    context -> restClient.get()
                            .uri(uriBuilder -> uriBuilder
                                    .pathSegment("tank-performance", "recent", region + ".json")
                                    .queryParam("mode", "recent")
                                    .queryParam("server", region)
                                    .build())
                            .retrieve()
                            .onStatus(HttpStatusCode::isError, (request, response) ->
                                    logger.error("[Tomato Import] HTTP Error {} calling {}", response.getStatusCode(), request.getURI()))
                            .body(JsonNode.class),
                    e -> {
                        logger.error("[Tomato Import] All retry attempts failed for region '{}', error: {}", region, e);
                        return null;
                    });

            if (root == null) {
                logger.warn("[Tomato Import] No JSON root received (region: {})", region);
                return null;
            }

            JsonNode pageProps = root.get("pageProps");
            if (pageProps == null) {
                logger.warn("[Tomato Import] Missing 'pageProps' node in response (region: {})", region);
                return null;
            }

            JsonNode data = pageProps.get("data");
            if (data == null) {
                logger.warn("[Tomato Import] Missing 'data' node in pageProps (region: {})", region);
                return null;
            }

            return objectMapper.treeToValue(data, TomatoTankPerformancesResponse.class);

        } catch (JsonProcessingException e) {
            logger.error("[Tomato Import] JSON mapping failed for region '{}'", region, e);
        } catch (Exception e) {
            logger.error("[Tomato Import] Unexpected error while processing response for region '{}'", region, e);
        }

        return null;
    }
}