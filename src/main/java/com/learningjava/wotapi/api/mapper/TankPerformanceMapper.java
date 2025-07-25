package com.learningjava.wotapi.api.mapper;

import com.learningjava.wotapi.api.model.tomato.dto.TomatoTankPerformanceResponse;
import com.learningjava.wotapi.api.model.tomato.entity.TankPerformance;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TankPerformanceMapper {
    TankPerformanceMapper INSTANCE = Mappers.getMapper(TankPerformanceMapper.class);

    TankPerformance toEntity(TomatoTankPerformanceResponse.PageProps.Data.TankPerformance dto);

    List<TankPerformance> toEntityList(List<TomatoTankPerformanceResponse.PageProps.Data.TankPerformance> dtos);
}
