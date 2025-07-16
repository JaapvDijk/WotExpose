package com.learningjava.wotapi.api.model.tomato.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "tank_performance",
        indexes = {
                @Index(name = "idx_name", columnList = "name"),
                @Index(name = "idx_import_date", columnList = "import_date")
        }
)
public class TankPerformance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate importDate;

    public int tank_id;
    public String name;
    public String nation;
    public int tier;
    public String tank_class;
    public String image;
    public String big_image;
    public int battles;
    public int player_wn8;
    public double winrate;
    public double player_winrate;
    public double winrate_differential;
    public int damage;
    public int sniper_damage;
    public double frags;
    public double shots_fired;
    public double direct_hits;
    public double penetrations;
    public double hit_rate;
    public double pen_rate;
    public int spotting_assist;
    public int tracking_assist;
    public double spots;
    public int damage_blocked;
    public int damage_received;
    public int potential_damage_received;
    public double base_capture_points;
    public double base_defense_points;
    public int life_time;
    public double survival;
    public int distance_traveled;
    public int base_xp;
    public int wn8;
    public boolean isPrem;

    public LocalDate getImportDate() {
        return importDate;
    }

    public void setImportDate(LocalDate importDate) {
        this.importDate = importDate;
    }

    public int getTank_id() {
        return tank_id;
    }

    public void setTank_id(int tank_id) {
        this.tank_id = tank_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getTank_class() {
        return tank_class;
    }

    public void setTank_class(String tank_class) {
        this.tank_class = tank_class;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBig_image() {
        return big_image;
    }

    public void setBig_image(String big_image) {
        this.big_image = big_image;
    }

    public int getBattles() {
        return battles;
    }

    public void setBattles(int battles) {
        this.battles = battles;
    }

    public int getPlayer_wn8() {
        return player_wn8;
    }

    public void setPlayer_wn8(int player_wn8) {
        this.player_wn8 = player_wn8;
    }

    public double getWinrate() {
        return winrate;
    }

    public void setWinrate(double winrate) {
        this.winrate = winrate;
    }

    public double getPlayer_winrate() {
        return player_winrate;
    }

    public void setPlayer_winrate(double player_winrate) {
        this.player_winrate = player_winrate;
    }

    public double getWinrate_differential() {
        return winrate_differential;
    }

    public void setWinrate_differential(double winrate_differential) {
        this.winrate_differential = winrate_differential;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSniper_damage() {
        return sniper_damage;
    }

    public void setSniper_damage(int sniper_damage) {
        this.sniper_damage = sniper_damage;
    }

    public double getFrags() {
        return frags;
    }

    public void setFrags(double frags) {
        this.frags = frags;
    }

    public double getShots_fired() {
        return shots_fired;
    }

    public void setShots_fired(double shots_fired) {
        this.shots_fired = shots_fired;
    }

    public double getDirect_hits() {
        return direct_hits;
    }

    public void setDirect_hits(double direct_hits) {
        this.direct_hits = direct_hits;
    }

    public double getPenetrations() {
        return penetrations;
    }

    public void setPenetrations(double penetrations) {
        this.penetrations = penetrations;
    }

    public double getHit_rate() {
        return hit_rate;
    }

    public void setHit_rate(double hit_rate) {
        this.hit_rate = hit_rate;
    }

    public double getPen_rate() {
        return pen_rate;
    }

    public void setPen_rate(double pen_rate) {
        this.pen_rate = pen_rate;
    }

    public int getSpotting_assist() {
        return spotting_assist;
    }

    public void setSpotting_assist(int spotting_assist) {
        this.spotting_assist = spotting_assist;
    }

    public int getTracking_assist() {
        return tracking_assist;
    }

    public void setTracking_assist(int tracking_assist) {
        this.tracking_assist = tracking_assist;
    }

    public double getSpots() {
        return spots;
    }

    public void setSpots(double spots) {
        this.spots = spots;
    }

    public int getDamage_blocked() {
        return damage_blocked;
    }

    public void setDamage_blocked(int damage_blocked) {
        this.damage_blocked = damage_blocked;
    }

    public int getDamage_received() {
        return damage_received;
    }

    public void setDamage_received(int damage_received) {
        this.damage_received = damage_received;
    }

    public int getPotential_damage_received() {
        return potential_damage_received;
    }

    public void setPotential_damage_received(int potential_damage_received) {
        this.potential_damage_received = potential_damage_received;
    }

    public double getBase_capture_points() {
        return base_capture_points;
    }

    public void setBase_capture_points(double base_capture_points) {
        this.base_capture_points = base_capture_points;
    }

    public double getBase_defense_points() {
        return base_defense_points;
    }

    public void setBase_defense_points(double base_defense_points) {
        this.base_defense_points = base_defense_points;
    }

    public int getLife_time() {
        return life_time;
    }

    public void setLife_time(int life_time) {
        this.life_time = life_time;
    }

    public double getSurvival() {
        return survival;
    }

    public void setSurvival(double survival) {
        this.survival = survival;
    }

    public int getDistance_traveled() {
        return distance_traveled;
    }

    public void setDistance_traveled(int distance_traveled) {
        this.distance_traveled = distance_traveled;
    }

    public int getBase_xp() {
        return base_xp;
    }

    public void setBase_xp(int base_xp) {
        this.base_xp = base_xp;
    }

    public int getWn8() {
        return wn8;
    }

    public void setWn8(int wn8) {
        this.wn8 = wn8;
    }

    public boolean isPrem() {
        return isPrem;
    }

    public void setPrem(boolean prem) {
        isPrem = prem;
    }
}