package com.learningjava.wotapi.infrastructure.persistance.mapper;

import com.learningjava.wotapi.infrastructure.client.wargaming.dto.WoTVehicleResponse;
import com.learningjava.wotapi.infrastructure.persistance.entity.worldoftanks.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    @Mapping(target = "id.tankId", source = "tankId")
    Vehicle toEntity(WoTVehicleResponse dto);
}