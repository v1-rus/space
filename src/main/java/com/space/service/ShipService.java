package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.domain.Pageable;
import sun.util.calendar.LocalGregorianCalendar;

import java.util.Date;
import java.util.List;

public interface ShipService {

    List<Ship> getAllShips(Pageable pageable);

    List<Ship> getAllWithShipTypeAndProdDate (ShipType shipType, java.sql.Date after,
                                              java.sql.Date before, Pageable pageable);

    Ship createShip(Ship ship);

    Ship getShipById(String id);

    void deleteShip(String id);

    Ship saveAndFlushShip(Ship ship);

    Long getCountWithFiltersPlanet(String planet);

    Long getCountWithFiltersShipTypeMaxCrewSize(ShipType shipType, Integer maxCrewSize);

    Long getCountWithFiltersNameAfterMaxRating(String name, Date after, Double maxRating);

    Long getCountWithFiltersMinRatingMinCrewSizeMinSpeed(Double minRating, Integer minCrewSize, Double minSpeed);

    Long getCountWithFiltersShipTypeBeforeMaxSpeed(ShipType shipType, Date before, Double maxSpeed);

    Long getCountWithFiltersIsUsedMinMaxSpeed(Boolean isUsed, Double minSpeed, Double maxSpeed);

    Long getCountWithFiltersShipTypeIsUsed(ShipType shipType, Boolean isUsed);

    Long getAllCount();

    List<Ship> getAllWithProdDateBetweenCrewSizeBetween(java.sql.Date after,
                                                        java.sql.Date before,
                                                        Integer minCrewSize,
                                                        Integer maxCrewSize,
                                                        Pageable pageable);

    List<Ship> getAllWithFiltersIsUsedMinMaxRating(Boolean isUsed,
                                                   Double minRating,
                                                   Double maxRating,
                                                   Pageable pageable);

    List<Ship> getAllWithIsUsedMaxSpeedMaxRating(Boolean isUsed,
                                                 Double maxSpeed,
                                                 Double maxRating,
                                                 Pageable pageable);

    List<Ship> getAllByPlanet(String planet, Pageable pageable);

    List<Ship> getAllByName(String name, Pageable pageable);

    List<Ship> getAllByShipTypeAndSpeedBetween(ShipType shipType,
                                Double minSpeed,
                                Double maxSpeed,
                                Pageable pageable);

    List<Ship> getAllByShipTypeAndCrewSizeBetween(ShipType shipType,
                                                  Integer minCrewSize,
                                                  Integer maxCrewSize,
                                                  Pageable pageable);
}
