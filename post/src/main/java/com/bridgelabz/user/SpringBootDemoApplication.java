package com.bridgelabz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bridgelabz.user.service.Services;

@SpringBootApplication
public class SpringBootDemoApplication {
	
	@Autowired
	Services services;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
}
