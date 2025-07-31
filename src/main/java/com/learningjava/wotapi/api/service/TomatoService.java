package com.learningjava.wotapi.api.service;

import com.learningjava.wotapi.api.mapper.TankPerformanceMapper;
import com.learningjava.wotapi.api.model.tomato.dto.TomatoTankPerformanceResponse;
import com.learningjava.wotapi.api.model.tomato.entity.TankPerformance;
import com.learningjava.wotapi.api.repo.TomatoTankPerformanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TomatoService {

    private final TomatoClient client;
    private final TomatoTankPerformanceRepository repo;
    private final TankPerformanceMapper mapper;

    public TomatoService(TomatoClient client, TomatoTankPerformanceRepository repo, TankPerformanceMapper mapper) {
        this.client = client;
        this.repo = repo;
        this.mapper = mapper;
    }

    public TomatoTankPerformanceResponse fetchTankPerformance(String region)
    {
        return client.getTankPerformance(region);
    }

    public void saveTankPerformance(TomatoTankPerformanceResponse tankPerformanceResponse, String region)
    {
        var localDate = LocalDate.now();
        var alreadyImportedToday = repo.existsByImportDateAndRegionEquals(localDate, region);
        if (alreadyImportedToday) {
           return;
        }

        var result = mapper.toEntityList(tankPerformanceResponse.getData());
        result = result.stream().peek(t ->
            {
                t.setImportDate(localDate);
                t.setRegion(region);
                t.setUpdated(tankPerformanceResponse.getMeta().getUpdated());
            }).toList();

        repo.saveAll(result);
    }

    public TankPerformance getRecentTankPerformanceByName(String name) {
        return repo.findLatestByName(name);
    }
}


