package com.bridgelabz.springboot.service;

import java.util.List;

import com.bridgelabz.springboot.dto.ForgotPasswordDto;
import com.bridgelabz.springboot.dto.LoginDto;
import com.bridgelabz.springboot.dto.RegistrationDto;
import com.bridgelabz.springboot.dto.ResetPasswordDto;
import com.bridgelabz.springboot.dto.Userdetails;
import com.bridgelabz.springboot.response.Response;

public interface UserService {

	public List<Userdetails> getAllUsers();

	public Userdetails getById(int id); // get users by id

	public void deleteUser(int Id); // delete users by id

	Response saveNewUser(RegistrationDto registrationDto); // save new users entry

	Response login(LoginDto loginDto, String token); // login user

	Response forgotPassword(ForgotPasswordDto forgotPasswordDto); // apply for forgot password

	public Response resetPassword(ResetPasswordDto resetPasswordDto, String token); // reset new password using verified
																					// (uniqe)token

	public String check(String email); // check for verification status

	public List getAllUsersdetails(); // get all users
}
