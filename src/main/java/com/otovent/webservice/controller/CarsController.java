package com.otovent.webservice.controller;

import com.otovent.webservice.entity.Cars;
import com.otovent.webservice.entity.request.CarRequest;
import com.otovent.webservice.entity.response.BaseResponse;
import com.otovent.webservice.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "cars")
public class CarsController {
    @Autowired
    CarsService carsService;

    // TODO get all car filtered by User
    @RequestMapping(value = "/get/by/user", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllCarsByUser(@RequestHeader Long id){
        List<Cars> result = carsService.getAllCarsByUserTarget(id);
        return BaseResponse.builder().httpStatus(HttpStatus.OK).message("Success Get One Car").result(result).build();
    }

    // TODO insert or edit car
    @RequestMapping(value = "/add-edit", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse insertCar(@RequestHeader CarRequest carRequest){
        List<Boolean> result = new ArrayList<>();
        result.add(carsService.addOrUpdateCar(carRequest));
        return BaseResponse.builder().httpStatus(HttpStatus.OK).message("Success Add a Car").result(result).build();
    }

    // TODO delete car by id car
    @RequestMapping(value = "/delete/by/id", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse deleteCarById(@RequestHeader Long id){
        List<Boolean> result = new ArrayList<>();
        result.add(carsService.deleteCar(id));
        return BaseResponse.builder().httpStatus(HttpStatus.OK).message("Success Get One Car").result(result).build();
    }
}
