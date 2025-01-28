package com.githubProjects.demo.dto.orderItem;

import com.githubProjects.demo.entities.OrderItem;

/**
 * This class represents a Data Transfer Object (DTO) for an Order Item. It
 * contains information about the product ID and the quantity of the product.
 */
public class OrderItemDTO {
	private Long productId; // Product ID
	private Integer quantity; // Quantity of the product

	public OrderItemDTO() {

	}

	public OrderItemDTO(OrderItem orderItem) {
		this.productId = orderItem.getId().getProduct().getId();
		this.quantity = orderItem.getQuantity();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	

}
