package com.otovent.webservice.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Entity
@Table
@Data
@Builder
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    @Tolerate
    Posts(){}
}
