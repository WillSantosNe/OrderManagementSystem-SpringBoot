package com.githubProjects.demo.dto.user;

import org.springframework.beans.BeanUtils;

import com.githubProjects.demo.entities.User;

/**
 * A DTO (Data Transfer Object) class for creating a new user.
 * This class includes all the necessary fields needed to create a user.
 */
public class CreateUserDTO {
	private String name;
	private String email;
	private String password;
	
	public CreateUserDTO() {
		
	}
	
	public CreateUserDTO(User user) {
		// Copying all attributes
		BeanUtils.copyProperties(user, this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
