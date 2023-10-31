package com.example.jwtauth.repo;

import com.example.jwtauth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<AppUser,Long> {

    @Query
    AppUser findByUserName(String userName);

}
