package com.learningjava.wotapi.application.service;

import com.learningjava.wotapi.infrastructure.model.dto.tomato.TomatoTankPerformancesResponse;
import com.learningjava.wotapi.infrastructure.mapper.TankPerformanceMapper;
import com.learningjava.wotapi.infrastructure.model.entity.tomato.TankPerformance;
import com.learningjava.wotapi.infrastructure.repo.TomatoTankPerformanceRepository;
import com.learningjava.wotapi.infrastructure.client.TomatoClient;
import com.learningjava.wotapi.shared.constant.Region;
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

    public TomatoTankPerformancesResponse fetchTankPerformance(String region)
    {
        return client.getTankPerformance(region);
    }

    public void saveTankPerformance(TomatoTankPerformancesResponse tankPerformanceResponse, String region)
    {
        var localDate = LocalDate.now();
        var alreadyImportedToday = repo.existsByImportDateAndRegionEquals(localDate, region);
        if (alreadyImportedToday) {
           return;
        }

        var result = mapper.toEntityList(tankPerformanceResponse.getData());
        result = result.stream().peek(t ->
            {
                t.setRegion(region);
                t.setUpdated(tankPerformanceResponse.getMeta().getUpdated());
            }).toList();

        repo.saveAll(result);
    }

    public TankPerformance getLatestTankPerformance(int tank_id, Region region) {
        return repo.getLatestByTankIdAndRegionEquals(tank_id, region);
    }
}


