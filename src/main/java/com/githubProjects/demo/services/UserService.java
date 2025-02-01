package com.githubProjects.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.githubProjects.demo.dto.user.CreateUserDTO;
import com.githubProjects.demo.dto.user.UpdateUserDTO;
import com.githubProjects.demo.dto.user.UserResponseDTO;
import com.githubProjects.demo.entities.User;
import com.githubProjects.demo.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Creates a new user in the database.
	 * 
	 * @param dto Data Transfer Object containing user details (name, email,
	 *            password).
	 * @return A UserResponseDTO with the newly created user's information (id,
	 *         name, email).
	 */
	public UserResponseDTO insert(CreateUserDTO dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());

		User savedUser = userRepository.save(user);
		return new UserResponseDTO(savedUser);
	}

	/**
	 * Updates an existing user by ID.
	 * 
	 * @param id  The ID of the user to update.
	 * @param dto Data Transfer Object containing updated user details (name,
	 *            email).
	 * @return A UserResponseDTO with the updated user's information (id, name,
	 *         email).
	 * @throws RuntimeException if the user with the specified ID is not found.
	 */
	public UserResponseDTO update(Long id, UpdateUserDTO dto) {
		try {
			User user = userRepository.getReferenceById(id);
			user.setName(dto.getName());
			user.setEmail(dto.getEmail());

			User updatedUser = userRepository.save(user);
			return new UserResponseDTO(updatedUser);
		} catch (EntityNotFoundException e) {
			throw new RuntimeException("User not found with ID: " + id);
		}
	}
}
