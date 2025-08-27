package com.learningjava.wotapi.infrastructure.dto.worldoftanks;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public final class WoTPlayerSimpleTankStatsResponse extends ArrayList<WoTPlayerSimpleTankStatResponse> {}
