package com.learningjava.wotapi.api.mapper;

import com.learningjava.wotapi.api.model.dto.UserResponse;
import com.learningjava.wotapi.api.model.entity.User;
import com.learningjava.wotapi.api.model.worldoftanks.dto.WoTVehicleResponse;
import com.learningjava.wotapi.api.model.worldoftanks.dto.entity.Vehicle;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    List<Vehicle> toEntityList(List<WoTVehicleResponse> vehicles);
}