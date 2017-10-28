package com.otovent.webservice.repository;

import com.otovent.webservice.entity.logs.LogEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LogEventsRepository extends JpaRepository<LogEvent,Long> {
    List<LogEvent> findAllByTimeOrderByTimeDesc(Date time);

}
