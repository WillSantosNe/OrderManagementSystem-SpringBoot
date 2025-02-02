package com.githubProjects.demo.dto.orderItem;

import com.githubProjects.demo.entities.OrderItem;

/**
 * Data Transfer Object (DTO) for returning order item details. Used to
 * encapsulate order item data when responding to API requests.
 */
public class OrderItemResponseDTO {
	private Long productId;
	private String productName; // Product name for display
	private Integer quantity;
	private Double unitPrice; // Unit price of the product
	private Double subtotal; // Total price of the item (unitPrice * quantity)

	public OrderItemResponseDTO() {
	}

	public OrderItemResponseDTO(OrderItem orderItem) {
		this.productId = orderItem.getProduct().getId();
		this.productName = orderItem.getProduct().getName();
		this.quantity = orderItem.getQuantity();
		this.unitPrice = orderItem.getProduct().getPrice();
		this.subtotal = orderItem.getSubtotal();
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
}
