package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Cars;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.service.CarsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarsServiceImpl implements CarsService{
    @Override
    public List<Cars> getAllCarsByUserTarget(User user) {
        return null;
    }

    @Override
    public void addOrUpdateCar(Cars car) {

    }

    @Override
    public void deleteCar(Long id) {

    }
}
