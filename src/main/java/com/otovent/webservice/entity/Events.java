package com.otovent.webservice.entity;

import com.otovent.webservice.entity.enums.StatusEntity;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@Builder
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String description;
    private String longitude;
    private String latitude;
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    private Date createdDate;

    private Date editedDate;

    private StatusEntity status;

    @Tolerate
    Events(){}
}
