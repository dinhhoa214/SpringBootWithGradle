package org.gradle.service.Impl;

import java.util.List;

import org.gradle.entity.User;
import org.gradle.responsitory.UserRepository;
import org.gradle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	public User insertUser(User user){
		
		if (checkUserByEmail(user.getEmail()))
			return null;
		userRepository.save(user);
		user.setPassword("");
		return user;
	}
	
	public List<User> getUser(){
		
		List<User> list = userRepository.findAll();
		list.forEach(us -> us.setPassword(""));
		return list;
	}
	
	public boolean checkUserByEmail(String email){
		User user = getUserByEmail(email);
		if (user == null) 
			return false;
		return true;
	}

	private User getUserByEmail(String email) {
		User user =  userRepository.findByEmail(email);
		return user;
	}

	@Override
	public List<User> deleteUser(String email) {
		if (checkUserByEmail(email)){
			User us = getUserByEmail(email);
			userRepository.delete(us);
		}
		return getUser();
	} 
}
