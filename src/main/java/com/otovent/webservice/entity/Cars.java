package com.otovent.webservice.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
@Builder
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String desc;
    private String imageLink;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;
}
