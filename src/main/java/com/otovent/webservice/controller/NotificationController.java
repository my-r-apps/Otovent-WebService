package com.otovent.webservice.controller;

import com.otovent.webservice.entity.Notification;
import com.otovent.webservice.entity.response.BaseResponse;
import com.otovent.webservice.entity.response.PaginationResponse;
import com.otovent.webservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "notification")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping(value = "/get/by/user/date", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaginationResponse getAllNotificationByUserAndDate(@RequestHeader Long idUser, @RequestHeader String date,
                                                              @RequestHeader Pageable pageable){
        Page<? extends Object> result = notificationService.getAllNotificationByUserAndDate(idUser,date,pageable);
        return PaginationResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Success")
                .totalPages(result.getTotalPages())
                .result(result)
                .build();
    }

    @GetMapping(value = "/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse readNotification(@RequestHeader Long idNotification){
        List<Boolean> result = new ArrayList<>();
        result.add(notificationService.readNotification(idNotification));
        return BaseResponse.builder()
                .message("Success")
                .result(result)
                .httpStatus(HttpStatus.OK)
                .build();
    }
}
