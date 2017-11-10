package com.otovent.webservice.service;

import com.otovent.webservice.entity.Events;
import com.otovent.webservice.entity.request.EventRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface EventService {
    Page<Events> getAllEventByUserAndCreatedDate(Long idUser, String date, Pageable pageable);
    List<Events> getAll();
    Events getOne(Long idEvent);
    Boolean add(EventRequest eventRequest);
    Boolean edit(EventRequest eventRequest);
    Boolean delete(Long idEvent);
    Boolean updateLinkImage(Long id, String urlImage);
}
