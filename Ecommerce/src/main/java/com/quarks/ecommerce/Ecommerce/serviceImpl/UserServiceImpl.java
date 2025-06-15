package com.quarks.ecommerce.Ecommerce.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quarks.ecommerce.Ecommerce.entities.UserTable;
import com.quarks.ecommerce.Ecommerce.repository.UserRepository;
import com.quarks.ecommerce.Ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserTable addUser(UserTable user) {
		// TODO Auto-generated method stub
		UserTable newUser = userRepository.save(user);
		return newUser;
	}

}
