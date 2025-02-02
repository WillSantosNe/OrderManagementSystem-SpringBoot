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

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserResponseDTO> insert(@RequestBody CreateUserDTO dto) {
		UserResponseDTO createdUser = userService.insert(dto);
		return ResponseEntity.ok(createdUser);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
		UserResponseDTO user = userService.findById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> findAll() {
		List<UserResponseDTO> users = userService.findAll();
		return ResponseEntity.ok(users);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @RequestBody UpdateUserDTO dto) {
		UserResponseDTO updatedUser = userService.update(id, dto);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
