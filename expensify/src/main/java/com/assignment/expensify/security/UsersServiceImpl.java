package com.assignment.expensify.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl  implements UserDetailsService{

	
	 @Autowired
	   UsersDBQuery usersDBQuery;

	   @Override
	   public UserHelper loadUserByUsername(final String username) throws UsernameNotFoundException {
	      UserEntity usersPojo = null;
	      try {
	    	  usersPojo = usersDBQuery.getUserDetails(username);
	    	  UserHelper userDetail = new UserHelper(usersPojo);
	         return userDetail;
	      } catch (Exception e) {
	         e.printStackTrace();
	         throw new UsernameNotFoundException("User " + username + " was not found in the database");
	      }
	   }
}
