package com.example.jwtauth.service.serviceInpl;

import com.example.jwtauth.model.AppUser;
import com.example.jwtauth.model.Role;
import com.example.jwtauth.repo.RoleRepo;
import com.example.jwtauth.repo.UserRepo;
import com.example.jwtauth.service.serviceInterface.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public AppUser saveUser(AppUser user) {
        logger.info("Added new user : "+user);
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return userRepo.save(user);
    }

    public Role saveRole(Role role) {
        logger.info("Added new role : "+role);
        return roleRepo.save(role);
    }

    @Override
    public void addRollToUser(String userName, String roleName) {
        logger.info("Added role {} to {} : ",roleName,userName);
        AppUser user = userRepo.findByUserName(userName);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String userName) {
        logger.info("Getting user : "+userName);
        return userRepo.findByUserName(userName);
    }

    @Override
    public List<AppUser> getAllUser() {
        logger.info("Getting all users : ");
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepo.findByUserName(username);

        if(appUser == null){
            logger.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }else {
            logger.info("User found {}",username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        appUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new User(appUser.getUserName(), appUser.getUserPassword(),authorities);
    }

    @Override
    public String deleteUser(String userName) {
        AppUser user = userRepo.findByUserName(userName);
        userRepo.delete(user);
        return "Deleted : "+userName;
    }
}
