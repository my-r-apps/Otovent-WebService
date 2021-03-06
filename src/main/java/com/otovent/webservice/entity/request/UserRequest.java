package com.otovent.webservice.entity.request;

import com.otovent.webservice.entity.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserRequest {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String photoProfile;

    private String email;

    private List<String> interests;

    private List<String> jobs;

    private Role role;
}
