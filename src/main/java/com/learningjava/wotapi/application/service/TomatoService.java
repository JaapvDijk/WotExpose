package com.learningjava.wotapi.application.service;

import com.learningjava.wotapi.infrastructure.client.tomato.dto.TomatoTankPerformancesResponse;
import com.learningjava.wotapi.infrastructure.persistance.mapper.TankPerformanceMapper;
import com.learningjava.wotapi.infrastructure.persistance.entity.tomato.TankPerformance;
import com.learningjava.wotapi.infrastructure.persistance.repo.TomatoTankPerformanceRepository;
import com.learningjava.wotapi.infrastructure.client.tomato.TomatoClient;
import com.learningjava.wotapi.shared.constant.RegionType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

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

    public TomatoTankPerformancesResponse fetchTankPerformance(RegionType region)
    {
        return client.getTankPerformance(region);
    }

    public void saveTankPerformance(TomatoTankPerformancesResponse tankPerformanceResponse, RegionType region)
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

    public Optional<TankPerformance> findLatestTankPerformance(int tankId, RegionType region) {
        return repo.findLatestByTankIdAndRegionEquals(tankId, region);
    }
}


