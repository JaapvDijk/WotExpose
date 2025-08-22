package com.learningjava.wotapi.api.mapper;

import com.learningjava.wotapi.api.model.dto.UserResponse;
import com.learningjava.wotapi.api.model.entity.User;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTVehicleResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    @Mapping(target = "id.tankId", source = "tankId")
    Vehicle toEntity(WoTVehicleResponse dto);

    List<Vehicle> toEntityList(List<WoTVehicleResponse> vehicles);
}