package com.otovent.webservice.entity.logs;

import com.otovent.webservice.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@Builder
public class LogUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date time;

    private String statusLog;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    @Tolerate
    LogUser(){}
}
