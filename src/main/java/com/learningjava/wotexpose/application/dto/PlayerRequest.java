package com.learningjava.wotexpose.application.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PlayerRequest extends PlayerRequestBase {

    public PlayerRequest(String region) {
        super(region);
    }
}
