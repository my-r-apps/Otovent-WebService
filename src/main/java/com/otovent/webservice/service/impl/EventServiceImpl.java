package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Events;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.repository.EventRepository;
import com.otovent.webservice.service.EventService;
import com.otovent.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service

public class EventServiceImpl implements EventService{
    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserService userService;

    @Override
    public Page<Events> getAllEventByUserAndCreatedDate(Long idUser, Date date, Pageable pageable) {
        User user = userService.getDetailOneUser(idUser);
        return eventRepository.findTop5ByUserAndCreatedDateOrderByCreatedDateDesc(user,date,pageable);
    }
}
