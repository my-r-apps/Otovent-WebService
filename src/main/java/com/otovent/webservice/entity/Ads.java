package com.otovent.webservice.entity;

import com.otovent.webservice.entity.enums.AdStatus;
import com.otovent.webservice.entity.enums.StatusEntity;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity
@Data
@Builder
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String imageLink;

    private Double donation;

    private StatusEntity status;

    private Date createdDate;

    private AdStatus confirmationStatus;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    @Tolerate
    Ads(){}
}
