package com.otovent.webservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Entity
@Table
@Data
@Builder
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Posts post;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    @Tolerate
    Comments(){}
}
