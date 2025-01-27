package com.githubProjects.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.githubProjects.demo.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Long>{

}
