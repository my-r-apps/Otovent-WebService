package com.otovent.webservice.entity.request;

import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.StatusEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PostRequest {
    private Long id;

    private String description;
    private String imageUrl;

    private User user;

    private Date createdDate;

    private Date editedDate;

    private StatusEntity status;
}
