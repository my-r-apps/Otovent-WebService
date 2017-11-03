package com.otovent.webservice.entity.request;

import com.otovent.webservice.entity.enums.StatusEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AdRequest {
    private Long id;
    private String name;
    private String desc;
    private String imageLink;
    private Long idUser;
    private Double donation;
    private StatusEntity status;
    private Date createdDate;
}
