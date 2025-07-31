package com.learningjava.wotapi.api.mapper;

import com.learningjava.wotapi.api.model.tomato.dto.TankPerformanceResponse;
import com.learningjava.wotapi.api.model.tomato.entity.TankPerformance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TankPerformanceMapper {

    @Mappings({
            @Mapping(source = "tank_id", target = "tankId"),
            @Mapping(source = "tank_class", target = "tankClass"),
            @Mapping(source = "big_image", target = "bigImage"),
            @Mapping(source = "player_wn8", target = "playerWn8"),
            @Mapping(source = "player_winrate", target = "playerWinrate"),
            @Mapping(source = "winrate_differential", target = "winrateDifferential"),
            @Mapping(source = "sniper_damage", target = "sniperDamage"),
            @Mapping(source = "shots_fired", target = "shotsFired"),
            @Mapping(source = "direct_hits", target = "directHits"),
            @Mapping(source = "hit_rate", target = "hitRate"),
            @Mapping(source = "pen_rate", target = "penRate"),
            @Mapping(source = "spotting_assist", target = "spottingAssist"),
            @Mapping(source = "tracking_assist", target = "trackingAssist"),
            @Mapping(source = "damage_blocked", target = "damageBlocked"),
            @Mapping(source = "damage_received", target = "damageReceived"),
            @Mapping(source = "potential_damage_received", target = "potentialDamageReceived"),
            @Mapping(source = "base_capture_points", target = "baseCapturePoints"),
            @Mapping(source = "base_defense_points", target = "baseDefensePoints"),
            @Mapping(source = "life_time", target = "lifeTime"),
            @Mapping(source = "distance_traveled", target = "distanceTraveled"),
            @Mapping(source = "base_xp", target = "baseXp")
    })
    TankPerformance toEntity(TankPerformanceResponse tank);

    List<TankPerformance> toEntityList(List<TankPerformanceResponse> tanks);
}
