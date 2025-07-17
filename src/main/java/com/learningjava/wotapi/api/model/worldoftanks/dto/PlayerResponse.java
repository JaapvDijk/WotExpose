package com.learningjava.wotapi.api.model.worldoftanks.dto;

public class PlayerResponse {
        public String nickname;
        public int account_id;

        public PlayerResponse(String nickname, int account_id) {
                this.nickname = nickname;
                this.account_id = account_id;
        }
}