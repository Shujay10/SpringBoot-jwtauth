package com.example.jwtauth.service.serviceInterface;

import com.example.jwtauth.model.AppUser;
import com.example.jwtauth.model.Role;

import java.util.List;

public interface UserService {

    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRollToUser(String userName,String roleName);
    AppUser getUser(String userName);
    List<AppUser> getAllUser();
    String deleteUser(String userName);

}
