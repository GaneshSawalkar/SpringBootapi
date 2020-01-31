package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class users {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "user")
	private String user;
	@Column(name = "pass")
	private String pass;
	@Column(name = "email")
	private String email;

	public users() {
	}

	public users(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return user;
	}

	public void setUsername(String username) {
		this.user = username;
	}

	public String getPassword() {
		return pass;
	}

	public void setPassword(String password) {
		this.pass = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
