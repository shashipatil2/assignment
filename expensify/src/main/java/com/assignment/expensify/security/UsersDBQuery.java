package com.assignment.expensify.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDBQuery {

	public UserEntity getUserDetails(String username) {
		Collection<GrantedAuthority> listOfgrantedAuthorities = new ArrayList<>();

		UserEntity user = new UserEntity();
		user.setUsername("admin");
		user.setPassword("admin");

		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
		listOfgrantedAuthorities.add(grantedAuthority);
		user.setListOfgrantedAuthorities(listOfgrantedAuthorities);
		return user;

	}
}
