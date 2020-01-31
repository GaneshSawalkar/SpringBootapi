package com.bridgelabz.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.springboot.dto.ForgotPasswordDto;
import com.bridgelabz.springboot.dto.LoginDto;
import com.bridgelabz.springboot.dto.RegistrationDto;
import com.bridgelabz.springboot.dto.ResetPasswordDto;
import com.bridgelabz.springboot.dto.Userdetails;
import com.bridgelabz.springboot.model.User;
import com.bridgelabz.springboot.repository.UserRepository;
import com.bridgelabz.springboot.response.Response;
import com.bridgelabz.springboot.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;

	@Autowired
	UserRepository repository;

	/**
	 * @param registrationDto :registration table entity
	 * @return response
	 */
	@PostMapping("/addUser") // register new users
	public ResponseEntity<Response> addUser(@RequestBody RegistrationDto registrationDto) {
		return new ResponseEntity<Response>(service.saveNewUser(registrationDto), HttpStatus.OK);
	}

	/**
	 * @param loginDto :login entity
	 * @param token    : unique generated token use for identify user authority
	 * @return response
	 */
	@PostMapping("/login") // user login
	public ResponseEntity<Response> login(@RequestBody LoginDto loginDto, @RequestParam(value = "token") String token) {
		return new ResponseEntity<Response>(service.login(loginDto, token), HttpStatus.OK);
	}

	/**
	 * @param forgotPasswordDto :forgot password entity
	 * @return response
	 */
	@PostMapping("/forgotPassword") // apply for new password
	public ResponseEntity<Response> forgotPassword(@RequestBody ForgotPasswordDto forgotPasswordDto) {
		return new ResponseEntity<Response>(service.forgotPassword(forgotPasswordDto), HttpStatus.OK);
	}

	/**
	 * @param resetPasswordDto :forgot password entity
	 * @param token            :unique generated token use for identify user
	 *                         authority
	 * @return response
	 */
	@PostMapping("/resetPassword") // reset users password
	public ResponseEntity<Response> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto,
			@RequestParam(value = "token") String token) {
		return new ResponseEntity<Response>(service.resetPassword(resetPasswordDto, token), HttpStatus.OK);
	}

	/**
	 * @return :all users list
	 */
	@GetMapping("/show/all") // show all users without password
	public ResponseEntity<List> list() {
		return new ResponseEntity<List>(service.getAllUsers(), HttpStatus.OK);
	}

	/**
	 * @return all users list with password
	 */
	@GetMapping("/show/alldetails") // show all users with password
	public ResponseEntity<List> alldetails() {
		return new ResponseEntity<List>(service.getAllUsersdetails(), HttpStatus.OK);
	}

	/**
	 * @param id :users unique identity number
	 * @return user info using id
	 */
	@GetMapping("/show/{id}") // show by id
	public Userdetails getById(@PathVariable int id) {
		Userdetails userdetails = service.getById(id);
		return userdetails;
	}

	/**
	 * @param id :users unique identity number
	 * @return delete user using id
	 */
	@DeleteMapping("/delete/{id}") // delete by id
	public String deleteUser(@PathVariable(value = "id") int id) {
		service.deleteUser(id);
		return "Deleted " + id;
	}

	/**
	 * @param email :users email id
	 * @return status for verified/pending/not in list
	 */
	@GetMapping("/verify/{email}") // check verification status using email
	public String isverifyUser(@PathVariable String email) {
		String str = service.check(email);
		return str;

	}

}
