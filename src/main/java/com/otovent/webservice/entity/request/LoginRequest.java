package com.otovent.webservice.entity.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    private String username,password;
}
