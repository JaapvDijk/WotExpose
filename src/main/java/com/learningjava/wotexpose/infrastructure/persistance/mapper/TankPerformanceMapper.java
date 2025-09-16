package com.learningjava.wotexpose.infrastructure.persistance.mapper;

import com.learningjava.wotexpose.infrastructure.client.tomato.dto.TomatoTankPerformanceResponse;
import com.learningjava.wotexpose.infrastructure.persistance.entity.tomato.TankPerformance;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TankPerformanceMapper {
    TankPerformance toEntity(TomatoTankPerformanceResponse tank);

    List<TankPerformance> toEntityList(List<TomatoTankPerformanceResponse> tanks);
}
