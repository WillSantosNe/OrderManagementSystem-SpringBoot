package com.githubProjects.demo.dto.order;

import java.util.List;
import java.util.Objects;

import com.githubProjects.demo.dto.orderItem.OrderItemDTO;
import com.githubProjects.demo.entities.Order;
import com.githubProjects.demo.entities.OrderStatus;

/**
 * Data Transfer Object (DTO) for updating an order. It encapsulates the
 * necessary information for updating an order, including a list of order items
 * and the order status.
 */
public class UpdateOrderDTO {

	private List<OrderItemDTO> items; // List of products and quantities in the order
	private OrderStatus status; // Status of the order, e.g. PENDING, SHIPPED, DELIVERED

	public UpdateOrderDTO() {
	}

	public UpdateOrderDTO(Order order) {
		this.items = order.getItems().stream().map(x -> new OrderItemDTO(x)).toList();
		this.status = order.getStatus();
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(items, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		UpdateOrderDTO other = (UpdateOrderDTO) obj;
		return Objects.equals(items, other.items) && status == other.status;
	}
}
