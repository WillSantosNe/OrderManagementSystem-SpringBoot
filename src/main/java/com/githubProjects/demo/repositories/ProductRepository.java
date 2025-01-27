package com.githubProjects.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.githubProjects.demo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
