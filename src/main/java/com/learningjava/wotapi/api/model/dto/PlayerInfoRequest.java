package com.learningjava.wotapi.api.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PlayerInfoRequest extends RequestBase {
    @NotBlank(message = "Id is required")
    @Size(min = 9, max = 9, message = "Id must be 9 characters long")
    private int id;

    public PlayerInfoRequest(int account_id, String region) {
        super(region);
        id = account_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
