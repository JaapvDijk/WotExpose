package com.learningjava.wotexpose.infrastructure.persistance.mapper;

import com.learningjava.wotexpose.infrastructure.client.wargaming.dto.WoTVehicleResponse;
import com.learningjava.wotexpose.infrastructure.persistance.entity.worldoftanks.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    @Mapping(target = "id.tankId", source = "tankId")
    @Mapping(target = "importDate", ignore = true)
    Vehicle toEntity(WoTVehicleResponse dto);
}