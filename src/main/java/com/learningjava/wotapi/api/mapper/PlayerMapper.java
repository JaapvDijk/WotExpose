package com.learningjava.wotapi.api.mapper;

import com.learningjava.wotapi.api.model.dto.PlayerSearchResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTPlayerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "accountId", source = "account_id")
    PlayerSearchResponse toDto(WoTPlayerResponse player);

    List<PlayerSearchResponse> toDtoList(List<WoTPlayerResponse> players);
}