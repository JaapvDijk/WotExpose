package com.learningjava.wotapi.api.model.worldoftanks.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.learningjava.wotapi.api.model.worldoftanks.WoTPlayerResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class WoTPlayersResponse extends ArrayList<WoTPlayerResponse> { }