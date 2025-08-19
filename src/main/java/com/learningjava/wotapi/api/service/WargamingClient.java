package com.learningjava.wotapi.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learningjava.wotapi.api.exception.PlayerNotFoundException;
import com.learningjava.wotapi.api.model.worldoftanks.dto.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.learningjava.wotapi.api.config.RestClientConfig.RestClientProxy;

import java.util.List;

@Component
public class WargamingClient {

    private final RestClientProxy restClient;
    private final ObjectMapper objectMapper;

    //TODO: add access token for private info .queryParam("access_token", access_token)
    public WargamingClient(@Qualifier("wargamingRestClient") RestClientProxy restClient,
                           ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
    }

    //Accounts
    public WoTPlayersResponse getPlayers(String name) {
        var root = restClient.getRequest()
                .uri(builder ->
                        builder.path("/account/list/")
                                .queryParam("application_id", "{application_id}")
                                .queryParam("search", name)
                                .build())
                .retrieve()
                .body(JsonNode.class);

        if (root == null || !root.has("data")) {
            throw new IllegalStateException("Response JSON missing 'data' node");
        }

        var dataNode = root.get("data");

        return objectMapper.convertValue(dataNode, WoTPlayersResponse.class);
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

        var dataNode = getData(accountId, root);

        return objectMapper.convertValue(dataNode, WoTPlayerInfoResponse.class);
    }

//    public WoTPlayerTanksResponse getPlayerTanks(int accountId) {
//        var root = restClient.getRequest()
//                .uri(builder ->
//                        builder.path("/account/tanks/")
//                                .queryParam("application_id", "{application_id}")
//                                .queryParam("account_id", accountId)
//                                .build())
//                .retrieve()
//                .body(JsonNode.class);
//
//        var dataNode = getData(accountId, root);
//
//        return objectMapper.convertValue(dataNode, WoTPlayerTanksResponse.class);
//    }

    public List<WoTPlayerTankStatResponse> getPlayerTanks(int accountId) {
        var root = restClient.getRequest()
                .uri(builder ->
                        builder.path("/tanks/stats/")
                                .queryParam("application_id", "{application_id}")
                                .queryParam("account_id", accountId)
                                .build())
                .retrieve()
                .body(JsonNode.class);

        var dataNode = getData(accountId, root);

        return objectMapper.convertValue(dataNode, WoTPlayerTankStatsResponse.class);
    }

    private JsonNode getData(int accountId, JsonNode root) {
        if (root == null || !root.has("data")) {
            throw new IllegalStateException("Response JSON missing 'data' node");
        }

        JsonNode dataNode = root.get("data").get(String.valueOf(accountId));
        if (dataNode == null || dataNode.isNull()) {
            throw new PlayerNotFoundException("No player found for account ID: " + accountId);
        }

        return dataNode;
    }

    //Player ratings
}
