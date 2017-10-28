package com.otovent.webservice.entity.request;

import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.StatusEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CarRequest {
    private Long id;

    private String desc;

    private String imageLink;

    private User user;

    private Date uploadDate;

    private Date editedDate;

    private StatusEntity status;
}
