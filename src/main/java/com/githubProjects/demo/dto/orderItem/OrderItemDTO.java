package com.githubProjects.demo.dto.orderItem;

import com.githubProjects.demo.entities.OrderItem;

/**
 * Data Transfer Object (DTO) for creating an order item.
 * Contains product ID and quantity of the product.
 */
public class OrderItemDTO {
    private Long productId; // Product ID
    private Integer quantity; // Quantity of the product

    public OrderItemDTO() {
    }

    public OrderItemDTO(OrderItem orderItem) {
        this.productId = orderItem.getProduct().getId();
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
