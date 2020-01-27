package com.example.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.model.users;

@Repository
@Service
public class servicesImp implements services {

	@Override
	public boolean isvaliduser(usersdao usersdao, String user) {
		List<users> list = (List<users>) usersdao.findAll();
		for (users users : list) {
			if (users.getUsername().equals(user)) {
				return false;
			}
		}
		return true;

	}

	@Override
	public boolean isvalidmail(usersdao usersdao, String user) {
		List<users> list = (List<users>) usersdao.findAll();
		for (users users : list) {
			if (users.getUsername().equals(user)) {
				return false;
			}
		}
		return true;
	}

	

}
