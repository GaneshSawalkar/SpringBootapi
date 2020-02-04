package com.bridgelabz.springboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.springboot.dto.ForgotPasswordDto;
import com.bridgelabz.springboot.dto.LoginDto;
import com.bridgelabz.springboot.dto.RegistrationDto;
import com.bridgelabz.springboot.dto.ResetPasswordDto;
import com.bridgelabz.springboot.dto.Userdetails;
import com.bridgelabz.springboot.model.User;
import com.bridgelabz.springboot.repository.UserRepository;
import com.bridgelabz.springboot.response.Response;
import com.bridgelabz.springboot.utility.Jms;
import com.bridgelabz.springboot.utility.Jwt;

@Service
@Transactional
@PropertySource("classpath:message.properties") // add path of message properties
public class UserServiceImpl implements UserService {

	@Resource(name = "redisTemplate") // redis template
	private SetOperations<String, String> setRepo;

	@Autowired
	private UserRepository repository; // user repository

	@Autowired
	private Jwt jwt; // json web token

	@Autowired
	private Jms jms; // java message service

	@Autowired
	private Environment environment; // environment for accessing message property

	@Autowired
	private BCryptPasswordEncoder bryBCryptPasswordEncoder; // cryptographic password encoder (cipher/plain form)

	@Autowired
	private ModelMapper modelMapper; // model-mapper

	public List<User> getAllUsersdetails() {
		List<User> user = repository.findAll();

		if (user != null) {
			return user; // return users list
		}
		return null; // empty repository
	}

	@Override
	public List<Userdetails> getAllUsers() {
		List<User> list = getAllUsersdetails();
		List<Userdetails> userlist = new ArrayList<Userdetails>();
		Userdetails[] userdetails = modelMapper.map(list, Userdetails[].class);
		for (Userdetails userdetails2 : userdetails) {
			userdetails2.setJoin(userdetails2.getJoin().toString());
			userdetails2.setModified(userdetails2.getModified().toString());
			userlist.add(userdetails2);
		}
		return userlist;
	}

	@Override
	public Userdetails getById(int id) {
		User user = repository.findById(id).get();// find user by id
		Userdetails userdetails = modelMapper.map(user, Userdetails.class);
		return userdetails;
	}

	@Override
	public void deleteUser(int id) {
		repository.deleteById(id); // delete user by its id
	}

	@Override
	public Response saveNewUser(RegistrationDto registrationDto) {
		User user = modelMapper.map(registrationDto, User.class);
		// find email is exist or not
		User userExist = repository.findByEmail(registrationDto.getEmail());
		if (userExist != null) { // if exist
			return new Response(environment.getProperty("SERVIER_CODE_ERROR"), environment.getProperty("USER_PRESENT"));
		} else { // if not exist
			if (registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
				String token = jwt.createToken(registrationDto.getEmail());
				jms.sendMail(registrationDto.getEmail(), token); // send token to usre's mail

				user.setPassword(bryBCryptPasswordEncoder.encode(user.getPassword())); // encrypted password

				Date date = new Date(); // set join & modified date of new user
				user.setJoin(date);
				user.setModified(date);

				repository.save(user); // save new user entry.
				return new Response(environment.getProperty("SERVIER_CODE_SUCCESS"),
						environment.getProperty("NEW_USER_CREATED"));
			}
		}

		return new Response(environment.getProperty("SERVIER_CODE_ERROR"),
				environment.getProperty("INVALID_CREDENTIALS")); // if user not show throw error
	}

	@Override
	public Response login(LoginDto loginDto, String token) {

		User user = modelMapper.map(loginDto, User.class);
		// find email is exist or not
		User userLogin = repository.findByEmail(user.getUserName());

		if (userLogin == null) { // if not exist
			System.out.println("User not found!");
		} else {
			String emailtoken = jwt.getUserToken(token.replace("Bearer ","").trim());
			if (isVerifyToken(emailtoken)) { // if exist
				boolean isUser = bryBCryptPasswordEncoder.matches(loginDto.getPassword(), userLogin.getPassword());
				if (isUser) {

					setRepo.add(token, userLogin.getEmail());
					System.out.println("added in redis");
					userLogin.setVerify(1);

					return new Response(environment.getProperty("SERVER_CODE_SUCCESS"),
							environment.getProperty("LOGIN_SUCCESS"));
				}
			}
		}
		// if login-dto objects is not match
		return new Response(environment.getProperty("SERVER_CODE_ERROR"),
				environment.getProperty("INVALID_CREDENTIALS"));
	}

	public Response forgotPassword(ForgotPasswordDto forgotPasswordDto) {
		User user = modelMapper.map(forgotPasswordDto, User.class);
		// find by email
		User userForget = repository.findByEmail(user.getEmail());
		if (userForget != null) { // if exist
			String token = jwt.createToken(user.getEmail()); // generate again new token for that email
			jms.sendMail(user.getEmail(), token); // send token to users mail id
			return new Response(environment.getProperty("SERVER_CODE_SUCCESS"),
					environment.getProperty("FORGOT_PASSWORD"));
		}
		// if not exist or wrong input
		return new Response(environment.getProperty("SERVER_CODE_ERROR"),
				environment.getProperty("INVALID_CREDENTIALS_RECOVERY"));
	}

	public Response resetPassword(ResetPasswordDto resetPasswordDto, String token) {
		User user = modelMapper.map(resetPasswordDto, User.class);
		// find by email
		String emailtoken = jwt.getUserToken(token);
		User userUpdate = repository.findByEmail(user.getEmail());
		if (userUpdate != null) {
			// if token verified then change password
			if (isVerifyToken(emailtoken)) {
				if (userUpdate.getEmail().equals(user.getEmail())) {
					if (resetPasswordDto.getConfirmPassword().equals(user.getPassword())) {
						// update(user, resetPasswordDto);
						userUpdate.setPassword(bryBCryptPasswordEncoder.encode(user.getPassword())); // set
																										// password
						Date date = new Date();
						userUpdate.setModified(date); // set new modified date

						repository.save(userUpdate);// save updates in repository
						return new Response(environment.getProperty("SERVER_CODE_SUCCESS"),
								environment.getProperty("RESET_SUCCESS"));
					}
				}

			} else {
				// if token is not match throw error
				return new Response(environment.getProperty("SERVER_CODE_ERROR"),
						environment.getProperty("TOKEN_ERROR"));
			}
		}
		// wrong input error
		return new Response(environment.getProperty("SERVER_CODE_ERROR"),
				environment.getProperty("INVALID_CREDENTIALS"));
	}

	private boolean isVerifyToken(String token) {
		// return true/false if token verify
		User verify = repository.findByEmail(token);
		if (verify != null) {
			return true;
		} else
			return false;
	}

	public String check(String email) { // check status of user verification
		List<User> list = repository.findAll();
		int find = -1;
		for (User user : list) {
			if (user.getEmail().equals(email)) {
				find = 0;
				if (user.getVerify() == 1) {
					find = 1;
				}
			}
		}
		if (find == 1) { // verified user
			return "verified email";
		} else if (find == 0) { // pending request
			return " email verification pending";
		} else { // not verified
			return "email not found";
		}
	}

}
