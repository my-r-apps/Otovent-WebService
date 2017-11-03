package com.otovent.webservice.entity;

import com.otovent.webservice.entity.enums.NotificationDependency;
import com.otovent.webservice.entity.enums.StatusNotification;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    private Long idPostEvent;
    private Long idCommentLike;
    private StatusNotification statusNotification;
    private NotificationDependency notificationDependency;
    private Date date;

    @Tolerate
    Notification () {}
}
