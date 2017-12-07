package com.example.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Service;

import com.example.entity.User;

@Service
@EnableGlobalMethodSecurity(securedEnabled=true) 
public class UserServiceImpl {
	
	@Secured("ROLE_ADMIN")
	public void addUser(User user) {
		System.out.println(user);
	}
	@Secured("ROLE_USER")
	public List<User> findAll() {
		System.out.println("----findAll----");
		return null;
	}
}
