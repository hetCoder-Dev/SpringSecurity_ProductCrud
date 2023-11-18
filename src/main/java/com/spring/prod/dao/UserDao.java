package com.spring.prod.dao;

import java.util.List;

import com.spring.prod.bean.UserBean;
import com.spring.prod.entity.User;
import com.spring.prod.entity.UserRole;

public interface UserDao {

	public void registerUser(User user);
	
	public void registerUser(UserBean user);
	
	public List<UserRole> getRoles();
	
	public User getUserByUsername(String email);
	
	public User getUserById(int id);
	
}
