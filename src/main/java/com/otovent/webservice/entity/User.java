package com.otovent.webservice.entity;

import com.otovent.webservice.entity.enums.Role;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
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

    private List<String> interests;

    private List<String> jobs;

    private Role role;
}
