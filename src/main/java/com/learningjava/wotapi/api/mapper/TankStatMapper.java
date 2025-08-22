package com.learningjava.wotapi.api.mapper;

import com.learningjava.wotapi.api.model.dto.PlayerTankStatResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayerTankStatResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TankStatMapper {

    PlayerTankStatResponse toPlayerTankStat(WoTPlayerTankStatResponse wotPlayerTankStat);

    List<PlayerTankStatResponse> toPlayerTankStats(List<WoTPlayerTankStatResponse> dtos);
}