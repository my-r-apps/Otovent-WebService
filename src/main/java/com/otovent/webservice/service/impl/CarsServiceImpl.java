package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Cars;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.StatusEntity;
import com.otovent.webservice.entity.request.CarRequest;
import com.otovent.webservice.repository.CarsRepository;
import com.otovent.webservice.service.CarsService;
import com.otovent.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CarsServiceImpl implements CarsService{
    @Autowired
    CarsRepository carsRepository;
    @Autowired
    UserService userService;

    @Override
    public Cars getOneCar(Long id) {
        return carsRepository.findOne(id);
    }

    @Override
    public List<Cars> getAllCarsByUserTarget(Long idUser) {
        User userTarget = userService.getDetailOneUser(idUser);
        List<Cars> result = carsRepository.findByUser(userTarget);
        return result;
    }

    @Override
    public Boolean addOrUpdateCar(CarRequest carRequest) {
        Cars carTarget;
        if(carRequest.getId().toString().isEmpty()){
            carTarget = Cars.builder()
                    .description(carRequest.getDesc())
                    .status(StatusEntity.ABLE)
                    .name(carRequest.getName())
                    .build();
        } else {
            carTarget = Cars.builder()
                    .id(carRequest.getId())
                    .description(carRequest.getDesc())
                    .status(StatusEntity.ABLE)
                    .name(carRequest.getName())
                    .editedDate(new Date())
                    .build();
        }
        carsRepository.save(carTarget);
        return Boolean.TRUE;
    }

    @Override
    public void updateLinkImageCar(Long id,String url) {
        Cars carTarget = carsRepository.findOne(id);
        carTarget.setImageLink(url);
        carTarget.setUploadDate(new Date());
        carsRepository.save(carTarget);
    }

    @Override
    public Boolean deleteCar(Long id) {
        Cars carTarget = carsRepository.findOne(id);
        carTarget.setStatus(StatusEntity.DELETED);
        return Boolean.TRUE;
    }
}
