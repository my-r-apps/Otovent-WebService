package com.otovent.webservice.entity.request;

import com.otovent.webservice.entity.enums.StatusEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EventRequest {
    private Long id;

    private String name;
    private String location;
    private String description;
    private String longitude;
    private String latitude;
    private String imageUrl;

    private Long idUser;

    private Date createdDate;

    private Date editedDate;

    private StatusEntity status;
}
