package com.example.serviceImp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.usersdto;
import com.example.model.users;
import com.example.service.services;
import com.example.userrepository.Usersrepository;

@Service
public class servicesImp implements services {

	@Autowired
	private Usersrepository usersrepo;

	@Autowired
	private servicesImp servicesImp;

	public String registerUser(usersdto user) {
		ModelMapper mapper = new ModelMapper();
		users newuser = mapper.map(user, users.class);
		usersrepo.save(newuser);
		return "saved";
	}

	public boolean loginform(usersdto user) {
		List<users> userslist = (List<com.example.model.users>) usersrepo.findAll();
		ModelMapper mapper = new ModelMapper();
		users obj = mapper.map(user, users.class);
		boolean find = false;
		for (users users2 : userslist) {
			if (users2.getUsername().equals(obj.getUsername()) && users2.getPassword().equals(obj.getPassword())) {
				System.out.println("correct");
				find = true;
				return find;

			}
		}
		return find;
	}

	public String updatepass(usersdto user) {
		List<users> users = (List<com.example.model.users>) usersrepo.findAll();
		boolean find = false;
		ModelMapper mapper = new ModelMapper();

		users userobj = mapper.map(user, users.class);
		for (users users2 : users) {
			if (users2.getUsername().equalsIgnoreCase(userobj.getUsername())) {
				users2.setPassword(userobj.getPassword());
				find = true;
			}
		}
		usersrepo.flush();
		for (users users2 : users) {
			usersrepo.save(users2);
		}
		if (find) {
			return "pass change";
		} else {
			return "not change";
		}
	}

}
