package com.learningjava.wotapi.application.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
public class PlayerRequest extends PlayerRequestBase {

    public PlayerRequest(String region) {
        super(region);
    }
}
