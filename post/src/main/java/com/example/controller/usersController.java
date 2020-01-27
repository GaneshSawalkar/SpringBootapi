package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.dao.usersdao;
import com.example.model.users;

@RestController
@RequestMapping("/user")
public class usersController {
	@Autowired
	private usersdao userlist;

	private static String myusername = null;

	@PostMapping("/register")
	public String add(@RequestBody users user) {
		userlist.save(user);
		System.out.println("successfully add");
		return "ok";
	}

	@PostMapping("/index")
	public String loginform(@RequestBody users user) {
		ModelAndView mav = new ModelAndView();
		List<users> users = (List<com.example.model.users>) userlist.findAll();
		boolean find = false;
		for (users users2 : users) {
			if (users2.getUsername().equals(user.getUsername()) && users2.getPassword().equals(user.getPassword())) {

				find = true;
				return "login success";

			}
		}
		if (!find) {
			mav.addObject("status", "incorrect user & password..!  please try again");
			mav.setViewName("index");
			return "not found failed";
		}
		return null;
	}

	@PostMapping("/pass")
	public String updatepass(@RequestBody users user) {
		ModelAndView mav = new ModelAndView();
		List<users> users = (List<com.example.model.users>) userlist.findAll();
		boolean find = false;
		for (users users2 : users) {
			if (users2.getUsername().equals(user.getUsername())) {
				users2.setPassword(user.getPassword());
				find = true;
			}
		}
		userlist.flush();
		for (users users2 : users) {
			userlist.save(users2);
		}
		if (find) {
			return "pass change";
		} else {
			return "not change";
		}
	}
}
