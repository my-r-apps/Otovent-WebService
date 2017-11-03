package com.otovent.webservice.entity.request;

import com.otovent.webservice.entity.enums.NotificationDependency;
import com.otovent.webservice.entity.enums.StatusNotification;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class NotificationRequest {
    private Long id;
    private Long user;
    private Long idPostEvent;
    private Long idCommentLike;
    private StatusNotification statusNotification;
    private NotificationDependency notificationDependency;
    private Date date;
}
