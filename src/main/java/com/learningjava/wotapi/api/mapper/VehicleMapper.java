package com.learningjava.wotapi.api.mapper;

import com.learningjava.wotapi.api.model.worldoftanks.WoTVehicleResponse;
import com.learningjava.wotapi.api.model.worldoftanks.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    @Mapping(target = "id.tankId", source = "tankId")
    Vehicle toEntity(WoTVehicleResponse dto);

    List<Vehicle> toEntityList(List<WoTVehicleResponse> vehicles);
}