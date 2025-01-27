package com.githubProjects.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.githubProjects.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
