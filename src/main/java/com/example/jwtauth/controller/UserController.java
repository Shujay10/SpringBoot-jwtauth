package com.example.jwtauth.controller;

import com.example.jwtauth.model.AppUser;
import com.example.jwtauth.model.Role;
import com.example.jwtauth.model.RoleToUserForm;
import com.example.jwtauth.service.serviceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getStat(){

        return "Running";
    }
    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAllUsers(){

        return ResponseEntity.ok().body(userService.getAllUser());
    }

    @GetMapping("/users/{userName}")
    public ResponseEntity<AppUser> getUser(@PathVariable("userName") String userName){
        return ResponseEntity.ok().body(userService.getUser(userName));
    }

    @PostMapping("/users")
    public ResponseEntity<AppUser> setUser(@RequestBody AppUser appUser){

        appUser.getRoles().add(new Role(null,"USER_USER"));
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(appUser));
    }

    @PostMapping("/role")
    public ResponseEntity<Role> setRole(@RequestBody Role role){

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addrtu")
    public ResponseEntity<?> setRoleToUser(@RequestBody RoleToUserForm form){

        userService.addRollToUser(form.getUserName(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable("username") String userName){
        return ResponseEntity.ok().body(userService.deleteUser(userName));
    }
}

