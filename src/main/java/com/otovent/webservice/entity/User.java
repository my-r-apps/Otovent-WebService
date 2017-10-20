package com.otovent.webservice.entity;

import com.otovent.webservice.entity.enums.Role;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "otovent_user")
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String photoProfile;

    private String email;

    private HashMap<String,String> interests = new HashMap<>();

    private HashMap<String,String> jobs = new HashMap<>();

    private HashMap<String,String> photos = new HashMap<>();

    private Role role;

    @Tolerate
    User(){}
}
