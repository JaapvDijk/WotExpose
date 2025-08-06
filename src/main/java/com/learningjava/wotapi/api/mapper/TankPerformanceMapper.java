package com.learningjava.wotapi.api.mapper;

import com.learningjava.wotapi.api.model.tomato.dto.TankPerformanceResponse;
import com.learningjava.wotapi.api.model.tomato.entity.TankPerformance;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TankPerformanceMapper {
    TankPerformance toEntity(TankPerformanceResponse tank);

    List<TankPerformance> toEntityList(List<TankPerformanceResponse> tanks);
}
