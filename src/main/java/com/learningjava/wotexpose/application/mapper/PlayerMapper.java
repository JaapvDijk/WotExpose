package com.learningjava.wotexpose.application.mapper;

import com.learningjava.wotexpose.application.dto.PlayerInfoResponse;
import com.learningjava.wotexpose.application.dto.PlayerSearchResponse;
import com.learningjava.wotexpose.infrastructure.client.wargaming.dto.WoTPlayerInfoResponse;
import com.learningjava.wotexpose.infrastructure.client.wargaming.dto.WoTPlayerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    PlayerSearchResponse toPlayerSearchResponse(WoTPlayerResponse player);
    List<PlayerSearchResponse> toPlayerSearchResponseList(List<WoTPlayerResponse> players);

    PlayerInfoResponse toPlayerInfoResponse(WoTPlayerInfoResponse infoResponse);
}