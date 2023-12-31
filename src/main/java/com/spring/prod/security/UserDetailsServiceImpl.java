package com.spring.prod.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.prod.dao.UserDao;
import com.spring.prod.entity.User;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDao.getUserByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Could Not find the User");
		}
		return new CustomUserDetails(user);
	}

}
