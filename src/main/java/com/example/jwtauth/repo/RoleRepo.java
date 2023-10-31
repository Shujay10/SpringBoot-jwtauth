package com.example.jwtauth.repo;

import com.example.jwtauth.model.AppUser;
import com.example.jwtauth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepo extends JpaRepository<Role,Long> {

    @Query
    Role findByName(String name);
}
