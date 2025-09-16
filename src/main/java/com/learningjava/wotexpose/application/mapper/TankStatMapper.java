package com.learningjava.wotexpose.application.mapper;

import com.learningjava.wotexpose.application.dto.PlayerTankStatResponse;
import com.learningjava.wotexpose.infrastructure.client.wargaming.dto.WoTPlayerTankStatResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TankStatMapper {
    PlayerTankStatResponse toPlayerTankStat(WoTPlayerTankStatResponse wotPlayerTankStat);

    List<PlayerTankStatResponse> toPlayerTankStatList(List<WoTPlayerTankStatResponse> dtos);
}