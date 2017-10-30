package com.otovent.webservice.service;

import com.otovent.webservice.entity.Cars;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.request.CarRequest;

import java.util.List;

public interface CarsService {
    Cars getOneCar(Long id);
    List<Cars> getAllCarsByUserTarget(Long idUser);
    Boolean addOrUpdateCar(CarRequest carRequest);
    void updateLinkImageCar(Long id, String url);
    Boolean deleteCar(Long id);
}
