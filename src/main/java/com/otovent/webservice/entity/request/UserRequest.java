package com.otovent.webservice.entity.request;

import com.otovent.webservice.entity.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
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

    private String address;

    private String email;

    private Date birthday;

    private String phoneNumber;

    private String linkFacebook;

    private String linkInstagram;

    private String linkTwitter;

    private List<String> interests;

    private List<String> jobs;

    private Role role;
}
