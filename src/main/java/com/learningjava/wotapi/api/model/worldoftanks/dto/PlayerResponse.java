package com.learningjava.wotapi.api.model.worldoftanks.dto;

public class PlayerResponse {
        public int id;
        public String name;

        public PlayerResponse(int id, String name) {
                this.id = id;
                this.name = name;
        }
}