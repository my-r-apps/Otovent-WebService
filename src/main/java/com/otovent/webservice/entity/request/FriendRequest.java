package com.otovent.webservice.entity.request;

import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.StatusEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FriendRequest {

    private Long id;

    private Long user;

    private Long friend;

    private Date dateFriend;

    private StatusEntity status;

}
