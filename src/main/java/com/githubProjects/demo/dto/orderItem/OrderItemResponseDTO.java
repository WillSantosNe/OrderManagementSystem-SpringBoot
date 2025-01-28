package com.githubProjects.demo.dto.orderItem;

import com.githubProjects.demo.entities.OrderItem;

/**
 * This class serves as a Data Transfer Object (DTO) for order items.
 * It is used to encapsulate the data related to individual items in an order
 * for the purpose of returning them as a part of a response from the API.
 */
public class OrderItemResponseDTO {
    private Long productId;
    private String productName; // Product name (optional, for display purpose)
    private Integer quantity;
    private Double unitPrice; // Unit price of the product
    private Double subtotal; // Total price of the item (unitPrice * quantity)

    public OrderItemResponseDTO() {

    }

    public OrderItemResponseDTO(OrderItem orderItem) {
        this.productId = orderItem.getId().getProduct().getId();
        this.quantity = orderItem.getQuantity();
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
