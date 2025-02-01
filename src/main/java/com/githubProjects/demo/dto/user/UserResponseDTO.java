package com.githubProjects.demo.dto.user;

import com.githubProjects.demo.entities.User;

// DTO to bring user data, without the password.
public class UserResponseDTO {
	private Long id;
	private String name;
	private String email;
	
	public UserResponseDTO() {
			
	}
	
	public UserResponseDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
}
