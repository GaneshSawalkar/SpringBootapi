package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.dto.usersdto;
import com.example.service.services;
import com.example.serviceImp.servicesImp;

@RestController
@RequestMapping("/user")
public class usersController {

	@Autowired
	private services serve = new servicesImp();

	@PostMapping("/register")
	public String reg(@RequestBody usersdto user) {
		String isSave = serve.registerUser(user);
		if (isSave.equalsIgnoreCase("saved")) {
			return isSave + " successfully";
		} else {
			return isSave + "failed";
		}

	}

	@PostMapping("/login")
	public String login(@RequestBody usersdto user) {
		ModelAndView mav = new ModelAndView();
		boolean status = serve.loginform(user);
		if (!status) {
			mav.addObject("status", "incorrect user & password..!  please try again");
			mav.setViewName("index");
			return "not found failed";
		} else {
			return "login sucess";
		}
	}

	@PostMapping("/recovery")
	public String updatepass(@RequestBody usersdto user) {
		ModelAndView mav = new ModelAndView();
		String status = serve.updatepass(user);
		return status;
	}

}
