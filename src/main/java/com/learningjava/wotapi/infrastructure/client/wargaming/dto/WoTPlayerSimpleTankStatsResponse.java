package com.learningjava.wotapi.infrastructure.client.wargaming.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import java.util.ArrayList;

@Generated("")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class WoTPlayerSimpleTankStatsResponse extends ArrayList<WoTPlayerSimpleTankStatResponse> {}
