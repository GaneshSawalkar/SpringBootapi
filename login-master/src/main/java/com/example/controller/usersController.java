package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.dao.services;
import com.example.dao.servicesImp;
import com.example.dao.usersdao;
import com.example.model.users;

@RestController
public class usersController {
	@Autowired
	private usersdao userlist;
	
	private services s = new servicesImp();
	private static Integer loginstatus = 0;
	private static Integer logoutstatus = 1;
	private static String myusername = null;

	// login user
	@GetMapping("/login")
	public static ModelAndView login() {
		ModelAndView mv = null;
		if (loginstatus == 0 && logoutstatus != 0) {
			mv = new ModelAndView("index");
			return mv;
		} else {

			mv = new ModelAndView("welcomeuser");
			mv.addObject("usern", myusername);
			return mv;
		}
	}

	@GetMapping("/index")
	public ModelAndView loginform(@RequestParam String username, @RequestParam String password) {
		ModelAndView mav = new ModelAndView();
		List<users> users = (List<com.example.model.users>) userlist.findAll();
		boolean find = false;
		for (users users2 : users) {
			if (users2.getUsername().equals(username) && users2.getPassword().equals(password)) {
				mav.setViewName("welcomeuser");
				myusername = username;
				mav.addObject("usern", username);
				loginstatus = 1;
				logoutstatus = 0;
				find = true;
			}
		}
		if (!find) {
			mav.addObject("status", "incorrect user & password..!  please try again");
			mav.setViewName("index");
		}
		return mav;
	}

	// logout user
	@RequestMapping("/logout")
	public ModelAndView logout(Map<String, Object> model) {
		model.put("status", "logout Successfull");
		ModelAndView mv = new ModelAndView("index");
		login();
		loginstatus = 0;
		logoutstatus = 1;
		myusername = null;
		return mv;
	}

	// display all users
	@GetMapping("/shows")
	public List<users> showsusers() {
		return (List<users>) userlist.findAll();
	}

	// register user
	@GetMapping("/reg")
	private ModelAndView registeruser() {
		ModelAndView mv = new ModelAndView("register");
		return mv;
	}

	@GetMapping("/register")
	public ModelAndView register(@RequestParam String newusername, @RequestParam String newuserpass,
			@RequestParam String newusermail) {
		users newuser = new users();
		newuser.setUsername(newusername);
		newuser.setPassword(newuserpass);
		newuser.setEmail(newusermail);
		if (s.isvaliduser(userlist, newusername)) {
			userlist.save(newuser);
		} else {
			ModelAndView mv = new ModelAndView("register");
			mv.addObject("exist", newusername);
			return mv;
		}
		ModelAndView mv = new ModelAndView("welcomeuser");
		mv.addObject("usern", newusername);
		return mv;
	}

	@GetMapping("/pass")
	public ModelAndView password() {
		ModelAndView mv = new ModelAndView("password-recovery");

		return mv;
	}

	@GetMapping("/recovery")
	public ModelAndView psswordrecovery(@RequestParam String usermail, @RequestParam String password) {
		ModelAndView mv = new ModelAndView("index");
		users user = new users();
		user.setPassword(password);
		user.setEmail(usermail);
		int update = s.updatepassword(userlist, user);
		return mv;
	}

}
