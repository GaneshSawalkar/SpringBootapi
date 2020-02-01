package com.bridgelabz.springboot.dto;

import java.io.Serializable;

public class LoginDto implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;

	private String email;

	private String password;

	public LoginDto() {
	}

	public LoginDto(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getUserName() {
		return email;
	}

	public void setUserName(String userName) {
		this.email = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
