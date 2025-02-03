package com.githubProjects.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.githubProjects.demo.dto.user.CreateUserDTO;
import com.githubProjects.demo.dto.user.UpdateUserDTO;
import com.githubProjects.demo.dto.user.UserResponseDTO;
import com.githubProjects.demo.services.UserService;

/**
 * Controller responsible for managing users. Provides endpoints for creating,
 * retrieving, updating, and deleting users.
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Retrieves a user by their ID.
	 *
	 * @param id The ID of the user to retrieve.
	 * @return ResponseEntity containing the user details.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
		UserResponseDTO user = userService.findById(id);
		return ResponseEntity.ok(user);
	}

	/**
	 * Retrieves all users.
	 *
	 * @return ResponseEntity containing a list of all users.
	 */
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> findAll() {
		List<UserResponseDTO> users = userService.findAll();
		return ResponseEntity.ok(users);
	}

	/**
	 * Creates a new user.
	 *
	 * @param dto The user data transfer object containing user details.
	 * @return ResponseEntity containing the created user details.
	 */
	@PostMapping
	public ResponseEntity<UserResponseDTO> insert(@RequestBody CreateUserDTO dto) {
		UserResponseDTO createdUser = userService.insert(dto);
		return ResponseEntity.ok(createdUser);
	}

	/**
	 * Updates an existing user.
	 *
	 * @param id  The ID of the user to update.
	 * @param dto The user update data transfer object containing updated details.
	 * @return ResponseEntity containing the updated user details.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UpdateUserDTO dto) {
		UserResponseDTO updatedUser = userService.update(id, dto);
		return ResponseEntity.ok(updatedUser);
	}

	/**
	 * Deletes a user by their ID.
	 *
	 * @param id The ID of the user to delete.
	 * @return ResponseEntity with no content if deletion is successful.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
