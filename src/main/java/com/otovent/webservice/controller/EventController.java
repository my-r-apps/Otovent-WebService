package com.otovent.webservice.controller;

import com.otovent.webservice.entity.Events;
import com.otovent.webservice.entity.request.EventRequest;
import com.otovent.webservice.entity.response.BaseResponse;
import com.otovent.webservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "event")
public class EventController {
    @Autowired
    EventService eventService;

    @GetMapping(value = "/get/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllData(){
        List<Events> result = eventService.getAll();
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success")
                .result(result)
                .build();
    }

    @GetMapping(value = "/get",produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getOne(@RequestHeader Long idEvent){
        List<Events> result = new ArrayList<>();
        result.add(eventService.getOne(idEvent));
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success")
                .result(result)
                .build();
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse add(@RequestBody EventRequest eventRequest){
        List<Events> result = new ArrayList<>();
        result.add(eventService.add(eventRequest));
        return BaseResponse.builder()
                .message("Success")
                .httpStatus(HttpStatus.OK)
                .result(result)
                .build();
    }

    @PostMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse edit(@RequestBody EventRequest eventRequest){
        List<Boolean> result = new ArrayList<>();
        result.add(eventService.edit(eventRequest));
        return BaseResponse.builder()
                .message("Success")
                .httpStatus(HttpStatus.OK)
                .result(result)
                .build();
    }

    @PostMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse delete(@RequestHeader Long idEvent){
        List<Boolean> result = new ArrayList<>();
        result.add(eventService.delete(idEvent));
        return BaseResponse.builder()
                .message("Success")
                .httpStatus(HttpStatus.OK)
                .result(result)
                .build();
    }
}
