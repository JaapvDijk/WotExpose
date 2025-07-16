package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.mapper.TankPerformanceMapper;
import com.learningjava.wotapi.api.model.tomato.dto.TomatoTankPerformanceResponse;
import com.learningjava.wotapi.api.model.tomato.entity.TankPerformance;
import com.learningjava.wotapi.api.repo.TomatoTankPerformanceRepository;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TomatoService {

    private final TomatoClient tomatoClient;
    private final TomatoTankPerformanceRepository repo;

    public TomatoService(TomatoClient tomatoClient, TomatoTankPerformanceRepository repo) {
        this.tomatoClient = tomatoClient;
        this.repo = repo;
    }

    public TomatoTankPerformanceResponse fetchTankPerformance()
    {
        return tomatoClient.getTankPerformance();
    }

    public void saveTankPerformance(TomatoTankPerformanceResponse tankPerformanceResponse)
    {
        var localDate = LocalDate.now();
        var alreadyImportedToday = repo.existsByImportDate(localDate);
        if (alreadyImportedToday) {
           return;
        }

        List<TankPerformance> result = TankPerformanceMapper.INSTANCE.toEntityList(tankPerformanceResponse.getData());
        result = result.stream().peek(t -> t.setImportDate(localDate)).toList();

        repo.saveAll(result);
    }

    public TankPerformance getRecentTankPerformanceByName(String name) {
        return repo.findLatestByName(name);
    }
}


