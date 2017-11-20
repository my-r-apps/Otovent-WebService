package com.otovent.webservice.repository;

import com.otovent.webservice.entity.Events;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.StatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EventRepository extends JpaRepository<Events,Long>{
    Page<Events> findTop5ByUserAndStatusOrderByCreatedDateDesc(User user, StatusEntity status,Pageable pageable);
}
