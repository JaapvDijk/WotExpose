package com.learningjava.wotapi.application.mapper;

import com.learningjava.wotapi.application.dto.PlayerTankStatResponse;
import com.learningjava.wotapi.infrastructure.client.wargaming.dto.WoTPlayerTankStatResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TankStatMapper {
    PlayerTankStatResponse toPlayerTankStat(WoTPlayerTankStatResponse wotPlayerTankStat);

    List<PlayerTankStatResponse> toPlayerTankStatList(List<WoTPlayerTankStatResponse> dtos);
}