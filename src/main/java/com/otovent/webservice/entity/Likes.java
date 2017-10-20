package com.otovent.webservice.entity;

import com.otovent.webservice.entity.enums.DEPENDENCY_TABLE;
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
    
    private DEPENDENCY_TABLE dependency_table;

    @Tolerate
    Likes(){}
}
