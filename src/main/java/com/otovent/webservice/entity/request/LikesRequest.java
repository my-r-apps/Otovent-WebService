package com.otovent.webservice.entity.request;

import com.otovent.webservice.entity.User;
import com.otovent.webservice.entity.enums.LikesDependency;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikesRequest {

    private Long id;

    private User user;

    private Long idTarget;

    private LikesDependency likesDependency;
}
