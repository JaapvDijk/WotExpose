package com.learningjava.wotapi.application.mapper;

import com.learningjava.wotapi.application.dto.PlayerInfoResponse;
import com.learningjava.wotapi.application.dto.PlayerSearchResponse;
import com.learningjava.wotapi.infrastructure.dto.worldoftanks.WoTPlayerInfoResponse;
import com.learningjava.wotapi.infrastructure.dto.worldoftanks.WoTPlayerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    PlayerSearchResponse toPlayerSearchResponse(WoTPlayerResponse player);
    List<PlayerSearchResponse> toPlayerSearchResponseList(List<WoTPlayerResponse> players);

    PlayerInfoResponse toPlayerInfoResponse(WoTPlayerInfoResponse infoResponse);
}