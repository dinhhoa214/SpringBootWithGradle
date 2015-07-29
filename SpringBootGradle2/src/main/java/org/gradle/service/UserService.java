package org.gradle.service;

import java.util.List;

import org.gradle.entity.User;
 

public interface UserService {
	
	User insertUser(User user); 
	
	List<User> getUser(); 

	List<User> deleteUser(String email);

	
}
