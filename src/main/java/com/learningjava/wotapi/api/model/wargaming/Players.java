package com.learningjava.wotapi.api.model.wargaming;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Players {
    public String status;
    public Meta meta;
    public ArrayList<Player> data;

    public static class Player{
        public String nickname;
        public int account_id;
    }

    public static class Meta{
        public int count;
    }
}

