package com.space.service;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.repository.CosmoportRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private CosmoportRepository cosmoportRepository;



    @Override
    public List<Ship> getAllShips(Pageable pageable) {
        return cosmoportRepository.findAllBy(pageable);
    }

    @Override
    public List<Ship> getAllWithShipTypeAndProdDate (ShipType shipType, java.sql.Date after,
                                                     java.sql.Date before, Pageable pageable) {
        return cosmoportRepository.findAllByShipTypeEqualsAndProdDateBetween(shipType, after, before, pageable);
    }

    @Override
    public Ship createShip(Ship ship) {
        return cosmoportRepository.save(ship);
    }

    @Override
    public Ship getShipById(String id) {
        Ship ship = cosmoportRepository.findById(Long.parseLong(id)).orElse(null);
        return ship;
    }

    @Override
    public void deleteShip(String id) {
        cosmoportRepository.deleteById(Long.parseLong(id));
    }

    @Override
    public Ship saveAndFlushShip(Ship ship) {
        return cosmoportRepository.saveAndFlush(ship);
    }

    @Override
    public Long getCountWithFiltersPlanet(String planet) {
        return cosmoportRepository.countAllByPlanetContains(planet);
    }

    @Override
    public Long getCountWithFiltersShipTypeMaxCrewSize(ShipType shipType, Integer maxCrewSize) {
        return cosmoportRepository.countAllByShipTypeEqualsAndCrewSizeBefore(shipType, maxCrewSize);
    }

    @Override
    public Long getCountWithFiltersNameAfterMaxRating(String name, Date after, Double maxRating) {
        return cosmoportRepository.countAllByNameContainsAndProdDateAfterAndRatingBefore(name, after, maxRating);
    }

    @Override
    public Long getCountWithFiltersMinRatingMinCrewSizeMinSpeed(Double minRating, Integer minCrewSize, Double minSpeed) {
        return cosmoportRepository.countAllByRatingAfterAndCrewSizeAfterAndSpeedAfter(minRating, minCrewSize, minSpeed);
    }

    @Override
    public Long getCountWithFiltersShipTypeBeforeMaxSpeed(ShipType shipType,Date before, Double maxSpeed) {
        return cosmoportRepository.countAllByShipTypeEqualsAndProdDateBeforeAndSpeedBefore(shipType, before, maxSpeed);
    }

    @Override
    public Long getCountWithFiltersIsUsedMinMaxSpeed(Boolean isUsed, Double minSpeed, Double maxSpeed) {
        return cosmoportRepository.countAllByIsUsedEqualsAndSpeedBetween(isUsed, minSpeed, maxSpeed);
    }

    @Override
    public Long getCountWithFiltersShipTypeIsUsed(ShipType shipType, Boolean isUsed) {
        return cosmoportRepository.countAllByShipTypeEqualsAndIsUsedEquals(shipType, isUsed);
    }

    @Override
    public Long getAllCount() {
        return cosmoportRepository.count();
    }

    @Override
    public List<Ship> getAllWithProdDateBetweenCrewSizeBetween(java.sql.Date after,
                                                               java.sql.Date before,
                                                               Integer minCrewSize,
                                                               Integer maxCrewSize,
                                                               Pageable pageable) {
        return cosmoportRepository.findAllByProdDateBetweenAndCrewSizeBetween(after, before,
                minCrewSize, maxCrewSize, pageable);
    }

    @Override
    public List<Ship> getAllWithFiltersIsUsedMinMaxRating(Boolean isUsed, Double minRating, Double maxRating, Pageable pageable) {
        return cosmoportRepository.findAllByIsUsedAndRatingBetween(isUsed, minRating, maxRating, pageable);
    }

    @Override
    public List<Ship> getAllWithIsUsedMaxSpeedMaxRating(Boolean isUsed,
                                                        Double maxSpeed,
                                                        Double maxRating,
                                                        Pageable pageable) {
        return cosmoportRepository.findAllByIsUsedEqualsAndSpeedBeforeAndAndRatingBefore(isUsed, maxSpeed, maxRating, pageable);
    }

    @Override
    public List<Ship> getAllByPlanet(String planet, Pageable pageable) {
        return cosmoportRepository.findAllByPlanetContains(planet, pageable);
    }

    @Override
    public List<Ship> getAllByName(String name, Pageable pageable) {
        return cosmoportRepository.findAllByNameContains(name, pageable);
    }

    @Override
    public List<Ship> getAllByShipTypeAndSpeedBetween(ShipType shipType,
                                                      Double minSpeed,
                                                      Double maxSpeed,
                                                      Pageable pageable) {
        return cosmoportRepository.findAllByShipTypeEqualsAndSpeedBetween(shipType, minSpeed, maxSpeed, pageable);
    }

    @Override
    public List<Ship> getAllByShipTypeAndCrewSizeBetween(ShipType shipType,
                                                         Integer minCrewSize,
                                                         Integer maxCrewSize,
                                                         Pageable pageable) {
        return cosmoportRepository.findAllByShipTypeEqualsAndCrewSizeBetween(
                shipType,
                minCrewSize,
                maxCrewSize,
                pageable);
    }
}
