package com.githubProjects.demo.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.githubProjects.demo.dto.order.CreateOrderDTO;
import com.githubProjects.demo.dto.order.OrderResponseDTO;
import com.githubProjects.demo.dto.order.UpdateOrderDTO;
import com.githubProjects.demo.entities.Order;
import com.githubProjects.demo.entities.OrderItem;
import com.githubProjects.demo.entities.OrderStatus;
import com.githubProjects.demo.entities.Product;
import com.githubProjects.demo.entities.User;
import com.githubProjects.demo.exceptions.ResourceNotFoundException;
import com.githubProjects.demo.repositories.OrderRepository;
import com.githubProjects.demo.repositories.ProductRepository;
import com.githubProjects.demo.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;
	
	
	
	 /**
     * Retrieves an order by ID.
     *
     * @param id The ID of the order to retrieve.
     * @return OrderResponseDTO containing order details.
     * @throws ResourceNotFoundException if the order is not found.
     */
    public OrderResponseDTO findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));
        return new OrderResponseDTO(order);
    }

    /**
     * Retrieves all orders.
     *
     * @return List of OrderResponseDTO containing all orders.
     */
    public List<OrderResponseDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(OrderResponseDTO::new).collect(Collectors.toList());
    }

	/**
	 * Creates a new order.
	 *
	 * @param dto DTO containing user ID and order items.
	 * @return OrderResponseDTO containing order details.
	 * @throws ResourceNotFoundException if user or any product does not exist.
	 */
	public OrderResponseDTO insert(CreateOrderDTO dto) {
		// Retrieve user by ID, throw an exception if not found
		User user = userRepository.findById(dto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + dto.getUserId()));

		// Create a new order and set initial properties
		Order order = new Order();
		order.setUser(user);
		order.setStatus(OrderStatus.PENDING);
		order.setOrderDate(Instant.now());

		// Convert DTO items to order items and add to order
		List<OrderItem> orderItems = dto.getItems().stream().map(itemDto -> {

			// Retrieve product by ID, throw an exception if not found
			Product product = productRepository.findById(itemDto.getProductId()).orElseThrow(
					() -> new ResourceNotFoundException("Product not found with ID: " + itemDto.getProductId()));

			// Validate quantity
			if (itemDto.getQuantity() <= 0) {
				throw new IllegalArgumentException("Quantity must be greater than zero.");
			}

			// Calculate subtotal and create order item
			Double subtotal = calculateSubtotal(product, itemDto.getQuantity());
			return new OrderItem(order, product, itemDto.getQuantity(), subtotal);
		}).collect(Collectors.toList());

		// Set order items to the order
		order.setItems(orderItems);

		// Save the order and return response DTO
		Order savedOrder = orderRepository.save(order);

		return new OrderResponseDTO(savedOrder);
	}

	/**
	 * Updates an existing order.
	 *
	 * @param id  The ID of the order to update.
	 * @param dto DTO containing updated order details.
	 * @return OrderResponseDTO containing updated order details.
	 * @throws ResourceNotFoundException if the order is not found.
	 */
	public OrderResponseDTO update(Long id, UpdateOrderDTO dto) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + id));

		if (dto.getStatus() != null) {
			order.setStatus(dto.getStatus());
		}

		if (dto.getItems() != null) {
			List<OrderItem> updatedItems = dto.getItems().stream().map(itemDto -> {
				Product product = productRepository.findById(itemDto.getProductId()).orElseThrow(
						() -> new ResourceNotFoundException("Product not found with ID: " + itemDto.getProductId()));

				if (itemDto.getQuantity() <= 0) {
					throw new IllegalArgumentException("Quantity must be greater than zero.");
				}

				Double subtotal = calculateSubtotal(product, itemDto.getQuantity());
				return new OrderItem(order, product, itemDto.getQuantity(), subtotal);
			}).collect(Collectors.toList());

			order.setItems(updatedItems);
		}

		Order updatedOrder = orderRepository.save(order);
		
		return new OrderResponseDTO(updatedOrder);
	}
	
	/**
     * Deletes an order by ID.
     *
     * @param id The ID of the order to delete.
     * @throws ResourceNotFoundException if the order does not exist.
     */
    public void delete(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found with ID: " + id);
        }
        orderRepository.deleteById(id);
    }

	/**
	 * Calculates the subtotal for an order item.
	 *
	 * @param product  The product being ordered.
	 * @param quantity The quantity of the product.
	 * @return The subtotal for the order item.
	 */
	private Double calculateSubtotal(Product product, Integer quantity) {
		return product.getPrice() * quantity;
	}
	

}
