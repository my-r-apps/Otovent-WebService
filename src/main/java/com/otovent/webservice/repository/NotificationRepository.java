package com.otovent.webservice.repository;

import com.otovent.webservice.entity.Notification;
import com.otovent.webservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long>{
    Page<Notification> findAllByUserAndDateOrderByDateDesc(User user, Date date, Pageable pageable);
}
