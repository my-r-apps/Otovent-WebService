package com.otovent.webservice.service.impl;

import com.otovent.webservice.entity.Events;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.logs.LogEvent;
import com.otovent.webservice.repository.LogEventsRepository;
import com.otovent.webservice.service.LogEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogEventServiceImpl implements LogEventService{
    @Autowired
    LogEventsRepository logEventsRepository;

    @Override
    public void insertEventLog(User user, Events event) {
        LogEvent log = LogEvent.builder()
                .user(user)
                .event(event)
                .time(new Date())
                .build();
        logEventsRepository.save(log);
    }

    @Override
    public List<LogEvent> getAllEventLogs(){
        return logEventsRepository.findAll();
    }

    @Override
    public List<LogEvent> getAllEventLogsByTime(String time) {
        return logEventsRepository.findAllByTimeOrderByTimeDesc(new Date(time));
    }
}
