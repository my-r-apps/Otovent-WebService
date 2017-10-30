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
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String imageLink;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    private Date uploadDate;

    private Date editedDate;

    private StatusEntity status;

    @Tolerate
    Cars(){}
}
