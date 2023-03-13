package com.assignment.expensify.security;


import org.springframework.security.core.userdetails.User;

public class UserHelper extends User{
 
	private static final long serialVersionUID = 1L;
	
	public UserHelper(UserEntity user) {
		super(user.getUsername(),user.getPassword(),user.getListOfgrantedAuthorities());
	}
	   
}