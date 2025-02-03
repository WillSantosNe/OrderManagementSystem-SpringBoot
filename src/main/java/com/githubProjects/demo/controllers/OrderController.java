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

import com.githubProjects.demo.dto.order.CreateOrderDTO;
import com.githubProjects.demo.dto.order.OrderResponseDTO;
import com.githubProjects.demo.dto.order.UpdateOrderDTO;
import com.githubProjects.demo.services.OrderService;

/**
 * Controller responsible for managing orders. Provides endpoints for creating,
 * retrieving, updating, and deleting orders.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * Retrieves an order by its ID.
	 *
	 * @param id The ID of the order to retrieve.
	 * @return ResponseEntity containing the order details.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponseDTO> findById(@PathVariable Long id) {
		OrderResponseDTO order = orderService.findById(id);
		return ResponseEntity.ok(order);
	}

	/**
	 * Retrieves all orders.
	 *
	 * @return ResponseEntity containing a list of all orders.
	 */
	@GetMapping
	public ResponseEntity<List<OrderResponseDTO>> findAll() {
		List<OrderResponseDTO> orders = orderService.findAll();
		return ResponseEntity.ok(orders);
	}

	/**
	 * Creates a new order.
	 *
	 * @param dto The order data transfer object containing order details.
	 * @return ResponseEntity containing the created order details.
	 */
	@PostMapping
	public ResponseEntity<OrderResponseDTO> insert(@RequestBody CreateOrderDTO dto) {
		OrderResponseDTO createdOrder = orderService.insert(dto);
		return ResponseEntity.ok(createdOrder);
	}

	/**
	 * Updates an existing order.
	 *
	 * @param id  The ID of the order to update.
	 * @param dto The order update data transfer object containing updated details.
	 * @return ResponseEntity containing the updated order details.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<OrderResponseDTO> update(@PathVariable Long id, @RequestBody UpdateOrderDTO dto) {
		OrderResponseDTO updatedOrder = orderService.update(id, dto);
		return ResponseEntity.ok(updatedOrder);
	}

	/**
	 * Deletes an order by its ID.
	 *
	 * @param id The ID of the order to delete.
	 * @return ResponseEntity with no content if deletion is successful.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		orderService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
