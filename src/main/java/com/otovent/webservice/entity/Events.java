package com.otovent.webservice.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

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

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;
}
