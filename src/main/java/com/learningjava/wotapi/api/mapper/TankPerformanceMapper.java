package com.learningjava.wotapi.api.mapper;

import com.learningjava.wotapi.api.model.tomato.dto.TomatoTankPerformanceResponse;
import com.learningjava.wotapi.api.model.tomato.entity.TankPerformance;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TankPerformanceMapper {
    TankPerformance toEntity(TomatoTankPerformanceResponse.TankPerformance dto);

    List<TankPerformance> toEntityList(List<TomatoTankPerformanceResponse.TankPerformance> dtos);
}
