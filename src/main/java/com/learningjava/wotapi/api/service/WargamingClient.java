package com.learningjava.wotapi.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayerInfoResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayersResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.learningjava.wotapi.api.config.RestClientConfig.RestClientProxy;

@Component
public class WargamingClient {

    private final RestClientProxy restClient;

    public WargamingClient(@Qualifier("wargamingRestClient") RestClientProxy restClient) {
        this.restClient = restClient;
    }

    //Accounts
    public WoTPlayersResponse getPlayers(String name) {
        return restClient.getRequest()
                .uri(builder ->
                        builder.path("/account/list/")
                                .queryParam("application_id", "{application_id}")
                                .queryParam("search", name)
                                .build())
                .retrieve()
                .body(WoTPlayersResponse.class);
    }

    public WoTPlayerInfoResponse getPlayerInfo(int accountId) {
        var root = restClient.getRequest()
                .uri(builder ->
                        builder.path("/account/info/")
                                .queryParam("application_id", "{application_id}")
                                .queryParam("account_id", accountId)
                                .build())
                .retrieve()
                .body(JsonNode.class);

        if (root == null || !root.has("data")) {
            throw new IllegalStateException("Response JSON missing 'data' node");
        }

        JsonNode dataNode = root.get("data").get(String.valueOf(accountId));
        if (dataNode == null || dataNode.isNull()) {
            throw new IllegalStateException("No player info found for account ID: " + accountId);
        }

        ObjectMapper jsonObjectMapper = new ObjectMapper();

        return jsonObjectMapper.convertValue(dataNode, WoTPlayerInfoResponse.class);
    }

    //Player ratings
}
