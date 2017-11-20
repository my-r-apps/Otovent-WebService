package com.otovent.webservice.entity;

import com.otovent.webservice.entity.enums.PostEventTimelineType;
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
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    private Date createdDate;

    private Date editedDate;

    private StatusEntity status;

    private String tipePostEvent = "POST";

    private PostEventTimelineType postEventTimelineType = PostEventTimelineType.POST;

    @Tolerate
    Posts(){}
}
