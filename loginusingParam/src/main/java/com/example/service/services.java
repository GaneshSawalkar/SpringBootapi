package com.example.service;

import com.example.dto.usersdto;

public interface services {

	public String registerUser(usersdto user);

	public boolean loginform(usersdto user);

	public String updatepass(usersdto user);

}
