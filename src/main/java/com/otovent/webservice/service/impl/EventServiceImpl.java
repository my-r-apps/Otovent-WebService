package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Events;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.StatusEntity;
import com.otovent.webservice.entity.request.EventRequest;
import com.otovent.webservice.repository.EventRepository;
import com.otovent.webservice.service.EventService;
import com.otovent.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service

public class EventServiceImpl implements EventService{
    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserService userService;

    @Override
    public Page<Events> getAllEventByUserAndCreatedDate(Long idUser, String dateRequested, Pageable pageable) {
        User user = userService.getDetailOneUser(idUser);
        DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        Date date = null;
        try {
            date = format.parse(dateRequested);
        } catch (ParseException e) {
            System.out.println(e.toString());
        }
        return eventRepository.findTop5ByUserAndCreatedDateAndStatusOrderByCreatedDateDesc(user,date,StatusEntity.ACTIVE,pageable);
    }

    @Override
    public List<Events> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Events getOne(Long idEvent) {
        return eventRepository.findOne(idEvent);
    }

    @Override
    public Boolean add(EventRequest eventRequest) {
        Events event = Events.builder()
                .createdDate(new Date())
                .description(eventRequest.getDescription())
                .latitude(eventRequest.getLatitude())
                .location(eventRequest.getLocation())
                .longitude(eventRequest.getLongitude())
                .name(eventRequest.getName())
                .status(StatusEntity.ACTIVE)
                .user(userService.getDetailOneUser(eventRequest.getIdUser()))
                .build();
        eventRepository.save(event);
        return Boolean.TRUE;
    }

    @Override
    public Boolean edit(EventRequest eventRequest) {
        Events event = Events.builder()
                .id(eventRequest.getId())
                .editedDate(new Date())
                .description(eventRequest.getDescription())
                .latitude(eventRequest.getLatitude())
                .location(eventRequest.getLocation())
                .longitude(eventRequest.getLongitude())
                .name(eventRequest.getName())
                .status(StatusEntity.ACTIVE)
                .user(userService.getDetailOneUser(eventRequest.getIdUser()))
                .build();
        eventRepository.save(event);
        return Boolean.TRUE;
    }

    @Override
    public Boolean delete(Long idEvent) {
        Events events = eventRepository.findOne(idEvent);
        events.setEditedDate(new Date());
        events.setStatus(StatusEntity.DELETED);
        eventRepository.save(events);
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateLinkImage(Long id, String urlImage) {
        Events events = eventRepository.findOne(id);
        events.setImageUrl(urlImage);
        eventRepository.save(events);
        return Boolean.TRUE;
    }
}
