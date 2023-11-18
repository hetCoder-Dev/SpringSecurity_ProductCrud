package com.spring.prod.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.spring.prod.entity.User;
import com.spring.prod.entity.UserRole;

public class UserBean {

	private int id;
	private String firstName;
	private String lastName;
	private String dob;
	private String email;
	private String mobile;
	private double balance;
	private String password;
	private String address;
	private boolean isValid;
	private String userRole;

	public UserBean(int id, String firstName, String lastName, String dob, String email, String mobile, double balance,
			String password, String address, boolean isValid, String userRole) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
		this.mobile = mobile;
		this.balance = balance;
		this.password = password;
		this.address = address;
		this.isValid = isValid;
		this.userRole = userRole;
	}

	public UserBean() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public User convertToUser() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//Date date = new Date();
		
		List<UserRole> roles = new ArrayList<>();
		
		User u = new User();
		u.setId(id);
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setEmail(email);
		u.setPassword(password);
		u.setMobile(mobile);		
		
		try {
			u.setDob(sdf.parse(dob));
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		u.setBalance(balance);

		UserRole ur = new UserRole();
		Integer roless = Integer.parseInt(userRole);
		ur.setId(roless);
		roles.add(ur);
		u.setUserRole(roles);

		return u;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", email=" + email + ", mobile=" + mobile + ", balance=" + balance + ", password=" + password
				+ ", address=" + address + ", isValid=" + isValid + ", userRole=" + userRole + "]";
	}

	
}
