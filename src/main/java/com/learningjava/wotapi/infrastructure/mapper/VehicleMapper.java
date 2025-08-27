package com.learningjava.wotapi.infrastructure.mapper;

import com.learningjava.wotapi.infrastructure.dto.worldoftanks.WoTVehicleResponse;
import com.learningjava.wotapi.infrastructure.model.entity.worldoftanks.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    @Mapping(target = "id.tankId", source = "tankId")
    Vehicle toEntity(WoTVehicleResponse dto);

    List<Vehicle> toEntityList(List<WoTVehicleResponse> vehicles);
}