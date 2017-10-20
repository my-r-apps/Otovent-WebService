package com.otovent.webservice.service;

import com.otovent.webservice.entity.Cars;
import com.otovent.webservice.entity.User;

import java.util.List;

public interface CarsService {
    List<Cars> getAllCarsByUserTarget(User user);
    void addOrUpdateCar(Cars car);
    void deleteCar(Long id);
}
