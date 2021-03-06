package com.example.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.model.users;

@Repository
public class servicesImp implements services {

	@Override
	public boolean isvaliduser(usersdao usersdao, String user) {
		List<users> list = (List<users>) usersdao.findAll();
		for (users users : list) {
			if (users.getUser().equals(user)) {
				return false;
			}
		}
		return true;

	}

	@Override
	public boolean isvalidmail(usersdao usersdao, String user) {
		List<users> list = (List<users>) usersdao.findAll();
		for (users users : list) {
			if (users.getUser().equals(user)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int updatepassword(usersdao userlist, users isuser) {

		List<users> ll = userlist.findAll();
		for (users users : ll) {
			if (users.getEmail().equals(isuser.getEmail())) {
				users.setPass(isuser.getPass());
			}

		}
		userlist.flush();
		for (users users : ll) {
			userlist.save(users);
		}
		return 0;
	}

	@Override
	public int updateuser(usersdao userlist, users isuser) {
		List<users> ll = userlist.findAll();
		for (users users : ll) {
			if (users.getUser().equals(isuser.getUser())) {
				users.setPass(isuser.getPass());
			}

		}
		userlist.flush();
		for (users users : ll) {
			userlist.save(users);
		}
		return 0;
	}

}
