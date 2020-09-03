package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class ShipController {

    @Autowired
    private ShipService shipService;


    @GetMapping("/ships")
    public List<Ship> getAllShips(@RequestParam(name = "name", required = false) String name,
                                  @RequestParam(name = "planet", required = false) String planet,
                                  @RequestParam(name = "shipType", required = false) ShipType shipType,
                                  @RequestParam(name = "after", required = false) Long after,
                                  @RequestParam(name = "before", required = false) Long before,
                                  @RequestParam(name = "isUsed", required = false) Boolean isUsed,
                                  @RequestParam(name = "minCrewSize", required = false) Integer minCrewSize,
                                  @RequestParam(name = "maxCrewSize", required = false) Integer maxCrewSize,
                                  @RequestParam(name = "minSpeed", required = false) Double minSpeed,
                                  @RequestParam(name = "maxSpeed", required = false) Double maxSpeed,
                                  @RequestParam(name = "minRating", required = false) Double minRating,
                                  @RequestParam(name = "maxRating", required = false) Double maxRating,
                                  @RequestParam(name = "order", required = false, defaultValue = "ID") ShipOrder order,
                                  @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "3") Integer pageSize) {

        if (name == null &&
                planet == null &&
                shipType == null &&
                after == null &&
                before == null &&
                isUsed == null &&
                minCrewSize == null &&
                maxCrewSize == null &&
                minSpeed == null &&
                maxSpeed == null &&
                minRating == null &&
                maxRating == null) {
            return shipService.getAllShips(pagingShips(pageNumber, pageSize, order));
        }

        else if (name == null &&
                planet == null &&
                isUsed == null &&
                minCrewSize == null &&
                maxCrewSize == null &&
                minSpeed == null &&
                maxSpeed == null &&
                minRating == null &&
                maxRating == null) {
            return shipService.getAllWithShipTypeAndProdDate(shipType, new java.sql.Date(after), new java.sql.Date(before),
                    PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName())));

        }

        else if (name == null &&
                planet == null &&
                shipType == null &&
                after == null &&
                before == null &&
                isUsed != null &&
                minCrewSize  == null &&
                maxCrewSize == null &&
                minSpeed  == null &&
                maxSpeed == null &&
                minRating != null &&
                maxRating != null) {
            return shipService.getAllWithFiltersIsUsedMinMaxRating(isUsed, minRating, maxRating,
                    PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName())));
        }

        else if (name == null &&
                planet == null &&
                shipType == null &&
                //after == null &&
                //before == null &&
                isUsed == null &&
                //minCrewSize == null &&
                //maxCrewSize == null &&
                minSpeed == null &&
                maxSpeed == null &&
                minRating == null &&
                maxRating == null) {
            return shipService.getAllWithProdDateBetweenCrewSizeBetween(
                    new java.sql.Date(after),
                    new java.sql.Date(before),
                    minCrewSize,maxCrewSize,
                    PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName())));
        }

        else if (name == null &&
                planet == null &&
                shipType == null &&
                after == null &&
                before == null &&
                //isUsed == null &&
                minCrewSize == null &&
                maxCrewSize == null &&
                minSpeed == null &&
                //maxSpeed == null &&
                minRating == null) {
                //maxRating == null)
            return shipService.getAllWithIsUsedMaxSpeedMaxRating(
                    isUsed,
                    maxSpeed,
                    maxRating,
                    PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName())));
        }

        else if (name == null &&
                //planet == null &&
                shipType == null &&
                after == null &&
                before == null &&
                isUsed == null &&
                minCrewSize == null &&
                maxCrewSize == null &&
                minSpeed == null &&
                maxSpeed == null &&
                minRating == null &&
                maxRating == null) {
            return shipService.getAllByPlanet(
                    planet,
                    PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName())));
        }

        else if (//name == null &&
                planet == null &&
                shipType == null &&
                after == null &&
                before == null &&
                isUsed == null &&
                minCrewSize == null &&
                maxCrewSize == null &&
                minSpeed == null &&
                maxSpeed == null &&
                minRating == null &&
                maxRating == null) {
            return shipService.getAllByName(
                    name,
                    PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName())));
        }

        else if (name == null &&
                planet == null &&
                //shipType == null &&
                after == null &&
                before == null &&
                isUsed == null &&
                minCrewSize == null &&
                maxCrewSize == null &&
                //minSpeed == null &&
                //maxSpeed == null &&
                minRating == null &&
                maxRating == null) {
            return shipService.getAllByShipTypeAndSpeedBetween(
                    shipType,
                    minSpeed,
                    maxSpeed,
                    PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName())));
        }

        else if (name == null &&
                planet == null &&
                //shipType == null &&
                after == null &&
                before == null &&
                isUsed == null &&
                //minCrewSize == null &&
                //maxCrewSize == null &&
                minSpeed == null &&
                maxSpeed == null &&
                minRating == null &&
                maxRating == null) {
            return shipService.getAllByShipTypeAndCrewSizeBetween(
                    shipType,
                    minCrewSize,
                    maxCrewSize,
                    PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName())));
        }
        return null;
    }

    @GetMapping("/ships/count")
    public Long getAllShipsCount(@RequestParam(name = "name", required = false) String name,
                                  @RequestParam(name = "planet", required = false) String planet,
                                  @RequestParam(name = "shipType", required = false) ShipType shipType,
                                  @RequestParam(name = "after", required = false) Long after,
                                  @RequestParam(name = "before", required = false) Long before,
                                  @RequestParam(name = "isUsed", required = false) Boolean isUsed,
                                  @RequestParam(name = "minCrewSize", required = false) Integer minCrewSize,
                                  @RequestParam(name = "maxCrewSize", required = false) Integer maxCrewSize,
                                  @RequestParam(name = "minSpeed", required = false) Double minSpeed,
                                  @RequestParam(name = "maxSpeed", required = false) Double maxSpeed,
                                  @RequestParam(name = "minRating", required = false) Double minRating,
                                  @RequestParam(name = "maxRating", required = false) Double maxRating) {
        if (name == null &&
                planet == null &&
                shipType == null &&
                after == null &&
                before == null &&
                isUsed == null &&
                minCrewSize  == null &&
                maxCrewSize == null &&
                minSpeed  == null &&
                maxSpeed == null &&
                minRating == null &&
                maxRating == null)
        return shipService.getAllCount();

        else if (name == null &&
                planet != null &&
                shipType == null &&
                after == null &&
                before == null &&
                isUsed == null &&
                minCrewSize  == null &&
                maxCrewSize == null &&
                minSpeed  == null &&
                maxSpeed == null &&
                minRating == null &&
                maxRating == null)
            return shipService.getCountWithFiltersPlanet(planet);

        else if (name == null &&
                planet == null &&
                shipType != null &&
                after == null &&
                before == null &&
                isUsed == null &&
                minCrewSize  == null &&
                maxCrewSize != null &&
                minSpeed  == null &&
                maxSpeed == null &&
                minRating == null &&
                maxRating == null)
            return shipService.getCountWithFiltersShipTypeMaxCrewSize(shipType, maxCrewSize);

        else if (name != null &&
                planet == null &&
                shipType == null &&
                after != null &&
                before == null &&
                isUsed == null &&
                minCrewSize  == null &&
                maxCrewSize == null &&
                minSpeed  == null &&
                maxSpeed == null &&
                minRating == null &&
                maxRating != null)

            return shipService.getCountWithFiltersNameAfterMaxRating(name, new java.sql.Date(after), maxRating);

        else if (name == null &&
                planet == null &&
                shipType == null &&
                after == null &&
                before == null &&
                isUsed == null &&
                minCrewSize  != null &&
                maxCrewSize == null &&
                minSpeed  != null &&
                maxSpeed == null &&
                minRating != null &&
                maxRating == null)
            return shipService.getCountWithFiltersMinRatingMinCrewSizeMinSpeed(minRating, minCrewSize, minSpeed);

        else if (name == null &&
                planet == null &&
                shipType != null &&
                after == null &&
                before != null &&
                isUsed == null &&
                minCrewSize  == null &&
                maxCrewSize == null &&
                minSpeed  == null &&
                maxSpeed != null &&
                minRating == null &&
                maxRating == null)
            return shipService.getCountWithFiltersShipTypeBeforeMaxSpeed(shipType, new java.sql.Date(before), maxSpeed);

        else if (name == null &&
                planet == null &&
                shipType == null &&
                after == null &&
                before == null &&
                isUsed != null &&
                minCrewSize  == null &&
                maxCrewSize == null &&
                minSpeed  != null &&
                maxSpeed != null &&
                minRating == null &&
                maxRating == null)
            return shipService.getCountWithFiltersIsUsedMinMaxSpeed(isUsed, minSpeed, maxSpeed);

        else if (name == null &&
                planet == null &&
                shipType != null &&
                after == null &&
                before == null &&
                isUsed != null &&
                minCrewSize  == null &&
                maxCrewSize == null &&
                minSpeed  == null &&
                maxSpeed == null &&
                minRating == null &&
                maxRating == null)
            return shipService.getCountWithFiltersShipTypeIsUsed(shipType, isUsed);
        else return null;
    }

    @PostMapping("/ships/{id}")
    public Ship updateShip(@PathVariable String id, @RequestBody Ship ship) {
        try {
            Long idLong = Long.parseLong(id);

            if(idLong == 0) throw new BadRequestException();

            if (shipService.getShipById(id) == null) throw new ShipNotFoundException();
            ship.setId(idLong);
            if (ship.getName() == null && ship.getPlanet() == null &&
                ship.getShipType() == null && ship.getProdDate() == null &&
                ship.getSpeed() == null && ship.getCrewSize() == null) {
                return shipService.getShipById(id);
            } else {
                if (ship.getName() == null) {
                    ship.setName(shipService.getShipById(id).getName());
                } else {
                    ship.setName(ship.getName());
                }
                if (ship.getName().isEmpty()) {
                    throw new BadRequestException();
                }
                if (ship.getPlanet() == null) {
                    ship.setPlanet(shipService.getShipById(id).getPlanet());
                } else {
                    ship.setPlanet(ship.getPlanet());
                }
                if (ship.getShipType() == null) {
                    ship.setShipType(shipService.getShipById(id).getShipType());
                } else {
                    ship.setShipType(ship.getShipType());
                }
                if (ship.getProdDate() == null) {
                    ship.setProdDate(shipService.getShipById(id).getProdDate());
                } else {
                    ship.setProdDate(ship.getProdDate());
                }
                if ((ship.getProdDate().getYear() + 1900) < 2800 ||
                        (ship.getProdDate().getYear() + 1900) > 3019) {
                    throw new BadRequestException();
                }
                if (ship.getUsed() == null) {
                    ship.setUsed(shipService.getShipById(id).getUsed());
                } else {
                    ship.setUsed(ship.getUsed());
                }
                if (ship.getSpeed() == null) {
                    ship.setSpeed(shipService.getShipById(id).getSpeed());
                } else {
                    ship.setSpeed(ship.getSpeed());
                }
                if (ship.getCrewSize() == null) {
                    ship.setCrewSize(shipService.getShipById(id).getCrewSize());
                } else {
                    ship.setCrewSize(ship.getCrewSize());
                }
                if (ship.getCrewSize() < 1 || ship.getCrewSize() > 9999) {
                    throw new BadRequestException();
                }
                Double rating = Math.rint(100.0 * ((80 * ship.getSpeed() * (ship.getUsed() ? 0.5 : 1)) / (3019 - (ship.getProdDate().getYear() + 1900) + 1))) / 100.0;
                ship.setRating(rating);
                return shipService.saveAndFlushShip(ship);
            }
                //if (ship.getName() == null) throw new BadRequestException();

        } catch (NumberFormatException e) {
            throw new BadRequestException();
        }
        //return null;
    }

    @PostMapping("/ships")
    public Ship createShip(@RequestBody Ship ship) {
        if (ship.getName() == null || ship.getPlanet() == null ||
                ship.getShipType() == null || ship.getProdDate() == null ||
                ship.getSpeed() == null || ship.getCrewSize() == null) {
            throw new BadRequestException();
        }

        if (ship.getName().length() > 50 || ship.getPlanet().length() > 50) {
            throw new BadRequestException();
        }
        if (ship.getName().isEmpty() || ship.getPlanet().isEmpty()) {
            throw new BadRequestException();
        }

        Double roundingSpeed = Math.rint(100.0 * ship.getSpeed()) / 100.0;
            if (ship.getSpeed() < 0.01 || ship.getSpeed() > 0.99) {
                throw new BadRequestException();
            }

            if (ship.getCrewSize() < 1 || ship.getCrewSize() > 9999) {
                throw new BadRequestException();
            }

            if (ship.getProdDate().getTime() < 0) {
                throw new BadRequestException();
            }

            if ((ship.getProdDate().getYear() + 1900) < 2800 || (ship.getProdDate().getYear() + 1900) > 3019) {
                throw new BadRequestException();
            }

            ship.setSpeed(roundingSpeed);

            if (ship.getUsed() == null) {
                ship.setUsed(false);

            } else {
                ship.setUsed(ship.getUsed());
            }

            Double rating = Math.rint(100.0 * ((80 * ship.getSpeed() * (ship.getUsed() ? 0.5 : 1)) /
                    (3019 - (ship.getProdDate().getYear() + 1900) + 1))) / 100.0;
            ship.setRating(rating);
            return shipService.createShip(ship);
    }

    @GetMapping("/ships/{id}")
    public Ship getShip(@PathVariable String id) {
        try {
            Long.parseLong(id);
            if(Long.parseLong(id) == 0) throw new BadRequestException();
            else if(shipService.getShipById(id) == null) throw new ShipNotFoundException();
            else if(!(shipService.getShipById(id).getId() > 0)) throw new BadRequestException();
            else return shipService.getShipById(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping("/ships/{id}")
    public void deleteShip(@PathVariable String id) {
        try {
            Long.parseLong(id);
            if(Long.parseLong(id) == 0) throw new BadRequestException();
            else if(shipService.getShipById(id) == null) throw new ShipNotFoundException();
            else if(!(shipService.getShipById(id).getId() > 0)) throw new BadRequestException();
            else  shipService.deleteShip(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException();
        }
    }

    private Pageable pagingShips(Integer pageNumber, Integer pageSize, ShipOrder shipOrder) {
        return PageRequest.of(pageNumber, pageSize, Sort.by(shipOrder.getFieldName()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    static class BadRequestException extends RuntimeException {
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    static class ShipNotFoundException extends RuntimeException {
    }
}
