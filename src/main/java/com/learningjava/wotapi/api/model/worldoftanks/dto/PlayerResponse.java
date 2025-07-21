package com.learningjava.wotapi.api.model.worldoftanks.dto;

public class PlayerResponse {
        private String nickname;
        private int account_id;

        public PlayerResponse(String nickname, int account_id) {
                this.nickname = nickname;
                this.account_id = account_id;
        }

        public String getNickname() {
                return nickname;
        }

        public void setNickname(String nickname) {
                this.nickname = nickname;
        }

        public int getAccount_id() {
                return account_id;
        }

        public void setAccount_id(int account_id) {
                this.account_id = account_id;
        }
}