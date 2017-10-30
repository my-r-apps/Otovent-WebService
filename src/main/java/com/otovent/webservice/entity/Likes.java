package com.otovent.webservice.entity;

import com.otovent.webservice.entity.enums.LikesDependency;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Table
@Entity
@Data
@Builder
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    private Long idTarget;
    
    private LikesDependency likesDependency;

    @Tolerate
    Likes(){}
}
