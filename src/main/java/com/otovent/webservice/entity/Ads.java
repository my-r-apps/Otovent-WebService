package com.otovent.webservice.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
@Builder
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String desc;
    private String imageLink;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

}
