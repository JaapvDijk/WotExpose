package com.learningjava.wotapi.api.mapper;

import com.learningjava.wotapi.api.model.worldoftanks.dto.PlayerResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayersResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerResponseMapper {
    PlayerResponseMapper INSTANCE = Mappers.getMapper(PlayerResponseMapper.class);

    PlayerResponse toDto(WoTPlayersResponse.Player dto);

    List<PlayerResponse> toDtoList(List<WoTPlayersResponse.Player> dtos);
}
