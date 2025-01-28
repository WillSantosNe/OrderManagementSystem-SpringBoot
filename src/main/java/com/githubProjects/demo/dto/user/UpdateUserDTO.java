package com.githubProjects.demo.dto.user;

import com.githubProjects.demo.entities.User;

/**
 * A DTO (Data Transfer Object) class for updating an existing user.
 * This class includes fields that can be updated (name, email), 
 * excluding unalterable fields such as password.
 */
public class UpdateUserDTO {
	private String name;
	private String email;
	// Do not include 'password' if it is not alterable via common update.
	
	public UpdateUserDTO() {
		
	}
	
	public UpdateUserDTO(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
