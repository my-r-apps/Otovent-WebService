package com.otovent.webservice.service;

import com.otovent.webservice.entity.Events;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface EventService {
    Page<Events> getAllEventByUserAndCreatedDate(Long idUser, Date date, Pageable pageable);
}
