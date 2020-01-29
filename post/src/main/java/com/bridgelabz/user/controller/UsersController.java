package com.bridgelabz.user.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.user.dto.UsersLoginDto;
import com.bridgelabz.user.dto.UsersPasswordRecoveryDto;
import com.bridgelabz.user.dto.UsersRegistrationDto;
import com.bridgelabz.user.model.Users;
import com.bridgelabz.user.responce.Responce;
import com.bridgelabz.user.service.Services;

@RestController
@RequestMapping("/user")
public class UsersController {

	@Autowired
	private Services serve;

	@PostMapping("/register")
	public Responce reg(@RequestBody UsersRegistrationDto usersRegistrationDto) {
		Responce isSave = serve.registerUser(usersRegistrationDto);
		return isSave;

	}

	@GetMapping("/login")
	public Responce login(@RequestBody UsersLoginDto usersLoginDto) {
		Responce responce = serve.loginform(usersLoginDto);
		return responce;
	}

	@PutMapping("/recovery")
	public Responce updatepass(@RequestBody UsersPasswordRecoveryDto usersPasswordRecoveryDto) {
		Responce responce = serve.updatepass(usersPasswordRecoveryDto);
		return responce;
	}

	@GetMapping("/show/{id}")
	public Optional<Users> showall(@PathVariable Integer id) {
		return serve.show(id);

	}

	@PostMapping("/verify")
	public void isverify(String username, String token) {

	}

}
