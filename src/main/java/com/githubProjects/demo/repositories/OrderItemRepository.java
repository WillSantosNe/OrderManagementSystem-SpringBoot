package com.githubProjects.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.githubProjects.demo.entities.OrderItem;
import com.githubProjects.demo.entities.pk.OrderItemPK;


public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

}
