package com.bridgelabz.user.service;

import java.util.Optional;

import com.bridgelabz.user.dto.UsersLoginDto;
import com.bridgelabz.user.dto.UsersPasswordRecoveryDto;
import com.bridgelabz.user.dto.UsersRegistrationDto;
import com.bridgelabz.user.model.Users;
import com.bridgelabz.user.responce.Responce;

public interface Services {

	public Responce registerUser(UsersRegistrationDto usersRegistrationDto);

	public Responce loginform(UsersLoginDto usersLoginDto);

	public Responce updatepass(UsersPasswordRecoveryDto usersPasswordRecoveryDto);

	public Optional<Users> show(Integer id);

	public boolean isExist(UsersRegistrationDto usersRegistrationDto);
}
