package com.learningjava.wotapi.infrastructure.mapper;

import com.learningjava.wotapi.infrastructure.dto.tomato.TomatoTankPerformanceResponse;
import com.learningjava.wotapi.infrastructure.model.entity.tomato.TankPerformance;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TankPerformanceMapper {
    TankPerformance toEntity(TomatoTankPerformanceResponse tank);

    List<TankPerformance> toEntityList(List<TomatoTankPerformanceResponse> tanks);
}
