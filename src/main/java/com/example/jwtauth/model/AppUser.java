package com.example.jwtauth.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity @Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String userName;
    String userPassword;
    @ManyToMany(fetch = FetchType.EAGER)
    Collection<Role> roles = new ArrayList<>();

    public AppUser() {
    }

    public AppUser(Long id, String name, String userName, String userPassword, Collection<Role> roles) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.userPassword = userPassword;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
