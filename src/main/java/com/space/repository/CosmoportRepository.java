package com.space.repository;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.util.calendar.LocalGregorianCalendar;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface CosmoportRepository  extends JpaRepository<Ship, Long> {

    List<Ship> findAllBy(Pageable pageable);

    List<Ship> findAllByShipTypeEqualsAndProdDateBetween(ShipType shipType, Date after,
                                                         Date before, Pageable pageable);

    List<Ship> findAllByIsUsedAndRatingBetween(Boolean isUsed, Double minRating, Double maxRating, Pageable pageable);

    List<Ship> findAllByProdDateBetweenAndCrewSizeBetween(Date after,
                                                          Date before,
                                                          Integer minCrewSize,
                                                          Integer maxCrewSize,
                                                          Pageable pageable);

    List<Ship> findAllByIsUsedEqualsAndSpeedBeforeAndAndRatingBefore(Boolean isUsed,
                                                                     Double maxSpeed,
                                                                     Double maxRating,
                                                                     Pageable pageable);

    List<Ship> findAllByPlanetContains(String planet, Pageable pageable);

    List<Ship> findAllByNameContains(String name, Pageable pageable);

    List<Ship> findAllByShipTypeEqualsAndSpeedBetween(ShipType shipType,
                                                      Double minSpeed,
                                                      Double maxSpeed,
                                                      Pageable pageable);

    List<Ship> findAllByShipTypeEqualsAndCrewSizeBetween(ShipType shipType,
                                                         Integer minCrewSize,
                                                         Integer maxCrewSize,
                                                         Pageable pageable);

    Optional<Ship> findById(Long id);

    void deleteById (Long id);

    Long countAllByPlanetContains(String planet);

    Long countAllByShipTypeEqualsAndCrewSizeBefore(ShipType shipType, Integer maxCrewSize);

    Long countAllByNameContainsAndProdDateAfterAndRatingBefore(String name, Date after, Double maxRating);

    Long countAllByRatingAfterAndCrewSizeAfterAndSpeedAfter(Double minRating, Integer minCrewSize, Double minSpeed);

    Long countAllByShipTypeEqualsAndProdDateBeforeAndSpeedBefore(ShipType shipType, Date before, Double maxSpeed);

    Long countAllByIsUsedEqualsAndSpeedBetween(Boolean isUsed, Double minSpeed, Double maxSpeed);

    Long countAllByShipTypeEqualsAndIsUsedEquals(ShipType shipType, Boolean isUsed);

}
