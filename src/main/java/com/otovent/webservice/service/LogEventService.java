package com.otovent.webservice.service;

import com.otovent.webservice.entity.Events;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.logs.LogEvent;

import java.util.Date;
import java.util.List;

public interface LogEventService {
    void insertEventLog(User user, Events event);
    List<LogEvent> getAllEventLogs();
    List<LogEvent> getAllEventLogsByTime(String time);
}
