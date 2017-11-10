package com.otovent.webservice.service;

import com.otovent.webservice.entity.Notification;
import com.otovent.webservice.entity.request.NotificationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface NotificationService {
    Page<? extends Object> getAllNotificationByUserAndDate(Long idUser, String dateRequested, Pageable pageable);
    Boolean addNotification(NotificationRequest notificationRequest);
    Boolean readNotification(Long idNotification);
    List<Notification> getAll();
    Page<Notification> getAllNewNotificationByUser(Long idUser, Pageable pageable);
}
