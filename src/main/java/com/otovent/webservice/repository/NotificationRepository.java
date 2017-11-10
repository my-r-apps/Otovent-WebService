package com.otovent.webservice.repository;

import com.otovent.webservice.entity.Notification;
import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.StatusNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long>{
    Page<Notification> findAllByUserAndStatusNotificationAndDateOrderByDateDesc(User user,
                                                                                StatusNotification statusNotification,
                                                                                Date date, Pageable pageable);
    Page<Notification> findAllByUserAndStatusNotificationOrderByDateDesc(User user, StatusNotification statusNotification,
                                                                          Pageable pageable);
}
