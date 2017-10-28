package com.otovent.webservice.controller;

import com.otovent.webservice.entity.response.BaseResponse;
import com.otovent.webservice.service.LogEventService;
import com.otovent.webservice.service.LogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "log")
public class LogController {
    @Autowired
    LogUserService logUserService;
    @Autowired
    LogEventService logEventService;

    // TODO : Get All Log Users
    @RequestMapping(method = RequestMethod.GET,value = "users/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllLogUsers(){
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success Get All Users Log")
                .result(logUserService.getAllLogUser())
                .build();
    }

    // TODO : Get All Log Users Filtered By Date
    @RequestMapping(method = RequestMethod.GET,value = "users/all/byDate",produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllLogUsersByDate(@RequestHeader String time){
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success Get All Log Users Filtered By Date")
                .result(logUserService.getAllLogUserByDate(time))
                .build();
    }

    // TODO : Get All Log Events
    @RequestMapping(method = RequestMethod.GET,value = "events/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllEventLog(){
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success Get All Log Events Filtered By Date")
                .result(logEventService.getAllEventLogs())
                .build();
    }

    // TODO : Get All Log Events Filtered By Date
    @RequestMapping(method = RequestMethod.GET,value = "events/all/byDate",produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllEventLogByDate(@RequestHeader String time){
        return BaseResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success Get All Log Events Filtered By Date")
                .result(logEventService.getAllEventLogsByTime(time))
                .build();
    }
}
