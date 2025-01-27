package com.githubProjects.demo.dto;

import com.githubProjects.demo.entities.User;

/**
 * A DTO (Data Transfer Object) class for updating an existing user.
 * This class includes fields that can be updated (name, email), 
 * excluding unalterable fields such as password.
 */
public class UpdateUserDTO {
	private String nome;
	private String email;
	// Do not include 'password' if it is not alterable via common update.
	
	public UpdateUserDTO() {
		
	}
	
	public UpdateUserDTO(User user) {
		this.nome = user.getName();
		this.email = user.getEmail();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
