package com.bridgelabz.user.serviceImp;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bridgelabz.user.dto.UsersLoginDto;
import com.bridgelabz.user.dto.UsersPasswordRecoveryDto;
import com.bridgelabz.user.dto.UsersRegistrationDto;
import com.bridgelabz.user.model.Users;
import com.bridgelabz.user.responce.Responce;
import com.bridgelabz.user.service.Services;
import com.bridgelabz.user.userrepository.UsersRepository;

@Service
public class ServicesImp implements Services {

	@Autowired
	private UsersRepository usersrepo;
	@Autowired
	Environment environment;

	public Responce registerUser(UsersRegistrationDto usersRegistrationDto) {
		ModelMapper mapper = new ModelMapper();
		Users newuser = mapper.map(usersRegistrationDto, Users.class);
		if (isExist(usersRegistrationDto)) {
			usersrepo.save(newuser);
			return new Responce(200, "user added!!!!");
		}
		return new Responce(400, "user added failed!!!!");
	}

	public Responce loginform(UsersLoginDto usersLoginDto) {
		List<Users> userslist = (List<com.bridgelabz.user.model.Users>) usersrepo.findAll();
		Responce responce = null;
		ModelMapper mapper = new ModelMapper();
		Users users = mapper.map(usersLoginDto, Users.class);
		boolean find = false;
		for (Users users2 : userslist) {
			if (users2.getUsername().equals(users.getUsername()) && users2.getPassword().equals(users.getPassword())) {
				find = true;
				System.out.println("correct");
				return new Responce(200, "successful login!!!!");
			}
		}
		if (!find) {
			responce = new Responce();
			return new Responce(400, "login failed");
		}
		return responce;

	}

	public Responce updatepass(UsersPasswordRecoveryDto usersPasswordRecoveryDto) {
		List<Users> users = (List<com.bridgelabz.user.model.Users>) usersrepo.findAll();
		boolean find = false;
		Responce responce;
		ModelMapper mapper = new ModelMapper();

		Users userobj = mapper.map(usersPasswordRecoveryDto, Users.class);
		for (Users users2 : users) {
			if (users2.getUsername().equalsIgnoreCase(userobj.getUsername())) {
				users2.setPassword(userobj.getPassword());
				find = true;
			}
		}
		usersrepo.flush();
		for (Users users2 : users) {
			usersrepo.save(users2);
		}
		if (find) {
			responce = new Responce(200, "password reset successfully!!!!");
		} else {
			responce = new Responce(400, "password change filed");
		}
		return responce;

	}

	@Override
	public Optional<Users> show(Integer id) {
		Optional<Users> user = usersrepo.findById(id);
		return user;
	}

	public boolean isExist(UsersRegistrationDto usersRegistrationDto) {
		List<Users> users = usersrepo.findAll();
		for (Users users2 : users) {
			if (users2.getUsername().equals(usersRegistrationDto.getUsername())
					&& users2.getEmail().equals(usersRegistrationDto.getEmail())) {
				return false;
			}
		}
		return true;
	}

}
