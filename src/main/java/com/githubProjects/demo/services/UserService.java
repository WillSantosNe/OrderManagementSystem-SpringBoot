package com.githubProjects.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.githubProjects.demo.dto.user.CreateUserDTO;
import com.githubProjects.demo.entities.User;
import com.githubProjects.demo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Adds a new user to the database.
	 *
	 * @param dto Data Transfer Object containing user details.
	 * @return The newly saved user.
	 */
	public User insert(CreateUserDTO dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());		
		return userRepository.save(user);
	}
}
