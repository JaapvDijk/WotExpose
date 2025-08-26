package com.learningjava.wotapi.api.mapper;

import com.learningjava.wotapi.api.model.dto.PlayerSearchResponse;
import com.learningjava.wotapi.api.model.worldoftanks.WoTPlayerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    PlayerSearchResponse toDto(WoTPlayerResponse player);

    List<PlayerSearchResponse> toDtoList(List<WoTPlayerResponse> players);
}