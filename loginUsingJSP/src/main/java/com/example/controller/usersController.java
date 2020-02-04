package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.dao.services;
import com.example.dao.servicesImp;
import com.example.dao.usersdao;
import com.example.model.users;

@RestController("/user")
public class usersController {
	boolean status;;
	@Autowired
	private usersdao userlist;

	private users newuser;

	private services service = new servicesImp();
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
			if (users2.getUser().equals(username) && users2.getPass().equals(password)) {
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
	@GetMapping("/registers")
	private ModelAndView registeruser() {
		ModelAndView mv = new ModelAndView("register");
		return mv;
	}

	@GetMapping("/register")
	public ModelAndView register(@RequestParam String email, @RequestParam String pass) {
		newuser = new users();
		newuser.setPass(pass);
		newuser.setEmail(email);
		newuser.setUser(email);
		if (service.isvaliduser(userlist, email)) {
			status = true;
		} else {
			ModelAndView mv = new ModelAndView("register");
			mv.addObject("exist", email);
			return mv;
		}
		ModelAndView mv = new ModelAndView("personalDetails");
		return mv;
	}

	@GetMapping("/personaldetails")
	public ModelAndView personaldetails(@RequestParam String fname, @RequestParam String lname,
			@RequestParam String address, @RequestParam String phone) {
		newuser.setAddress(address);
		newuser.setFname(fname);
		newuser.setLname(lname);
		newuser.setPhone(phone);
		userlist.save(newuser);
		ModelAndView mv = new ModelAndView("welcomeuser");

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
		user.setPass(password);
		user.setEmail(usermail);
		int update = service.updatepassword(userlist, user);
		return mv;
	}

}
