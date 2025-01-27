package com.githubProjects.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.githubProjects.demo.repositories.OrderRepository;

public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	//@Transactional(readOnly = true)
	
}
