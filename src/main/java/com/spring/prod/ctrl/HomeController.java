package com.spring.prod.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.prod.bean.UserBean;
import com.spring.prod.dao.UserDao;
import com.spring.prod.entity.User;
import com.spring.prod.entity.UserRole;

@Controller
public class HomeController {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	@RequestMapping("/register")
	public String registerForm(Model m) {
		System.out.println("User dao:: " + userDao);
		List<UserRole> roles = userDao.getRoles();
		m.addAttribute("pageTitle", "Register Page");
		m.addAttribute("roles", roles);
		return "register";

	}
	
	@PostMapping("/registrationValid")
	public String processRegi(@ModelAttribute UserBean user, Model m, @RequestParam("userRole") String role) {
		System.out.println("in post regi");
		System.out.println("UserRole " + role);
		User userByEmail = userDao.getUserByUsername(user.getEmail());
		if (null != userByEmail && userByEmail.getEmail().equals(user.getEmail())) {
			m.addAttribute("msg", "Email already exists");
			return "register";
		}
		
		else {
			System.out.println(user.getUserRole());
			userDao.registerUser(user);
			m.addAttribute("role", role);
			return "home";
		}
		
	}
}
