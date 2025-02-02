package com.githubProjects.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.githubProjects.demo.dto.user.CreateUserDTO;
import com.githubProjects.demo.dto.user.UpdateUserDTO;
import com.githubProjects.demo.dto.user.UserResponseDTO;
import com.githubProjects.demo.entities.User;
import com.githubProjects.demo.exceptions.ResourceNotFoundException;
import com.githubProjects.demo.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Retrieves a user by ID.
	 * 
	 * @param id The ID of the user to retrieve.
	 * @return A UserResponseDTO with the user's information.
	 * @throws ResourceNotFoundException if the user with the specified ID is not
	 *                                   found.
	 */
	@Transactional
	public UserResponseDTO findById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
		return new UserResponseDTO(user);
	}

	/**
	 * Retrieves all users.
	 * 
	 * @return A list of UserResponseDTOs containing all users.
	 */
	@Transactional
	public List<UserResponseDTO> findAll() {
		List<User> users = userRepository.findAll();
		return users.stream().map(UserResponseDTO::new).collect(Collectors.toList());
	}

	/**
	 * Creates a new user in the database.
	 * 
	 * @param dto Data Transfer Object containing user details (name, email,
	 *            password).
	 * @return A UserResponseDTO with the newly created user's information (id,
	 *         name, email).
	 */
	public UserResponseDTO insert(CreateUserDTO dto) {

		// Check if the email is already in use
		if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
			throw new IllegalArgumentException("Email is already in use: " + dto.getEmail());
		}

		// Validate that Name and Email fields are not null or empty
		if (dto.getName() == null || dto.getName().isEmpty() || dto.getEmail() == null || dto.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Name and Email are required fields.");
		}

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
	@Transactional
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

	/**
	 * Deletes a user by ID.
	 *
	 * @param id The ID of the user to delete.
	 * @throws ResourceNotFoundException if the user with the specified ID does not
	 *                                   exist.
	 */
	public void delete(Long id) {
		if (!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("User not found with ID: " + id);
		}
		userRepository.deleteById(id);
	}

}
