package com.example.jwtauth;

import com.example.jwtauth.model.AppUser;
import com.example.jwtauth.model.Role;
import com.example.jwtauth.service.serviceInterface.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_SUPERADMIN"));

			userService.saveUser(new AppUser(null,"Shujay","Shujay10","123",new ArrayList<Role>()));
			userService.saveUser(new AppUser(null,"Aish","Aish26","123",new ArrayList<Role>()));
			userService.saveUser(new AppUser(null,"Karthi","Karthi06","123",new ArrayList<Role>()));

			userService.addRollToUser("Aish26","ROLE_USER");
			userService.addRollToUser("Karthi06","ROLE_ADMIN");
			userService.addRollToUser("Shujay10","ROLE_SUPERADMIN");

		};
	}

}
