package com.spring.prod.ctrl;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.prod.dao.UserDao;
import com.spring.prod.entity.User;

@Controller
public class LoginController {
	
	@Autowired
	private UserDao userDao;

	@RequestMapping("/")
	public String home(Model m, Principal principal, HttpSession sess) {
		
		String username = principal.getName();
		User login = userDao.getUserByUsername(username);
		System.out.println("User in ctrl "+login);
		sess.setAttribute("auth", login);
		return "welcome";
	}
	
	@RequestMapping("/403")
    public String deniedPage() {
    	return "403";
    }

}
