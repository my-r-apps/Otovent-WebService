package com.otovent.webservice.entity.logs;

import com.otovent.webservice.entity.Events;
import com.otovent.webservice.entity.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
@Builder
public class LogEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Events event;
}
