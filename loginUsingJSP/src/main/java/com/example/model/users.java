package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	@Column(name = "fname")
	private String fname;
	@Column(name = "lname")
	private String lname;
	@Column(name = "phone")
	private String phone;
	@Column(name = "address")
	private String address;

	public users() {
	}

	public users(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

}
