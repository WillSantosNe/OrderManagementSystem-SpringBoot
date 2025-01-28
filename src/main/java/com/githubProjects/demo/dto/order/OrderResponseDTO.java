package com.githubProjects.demo.dto.order;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import com.githubProjects.demo.dto.orderItem.OrderItemResponseDTO;
import com.githubProjects.demo.entities.Order;
import com.githubProjects.demo.entities.OrderStatus;

/**
 * Data Transfer Object (DTO) for order response. It encapsulates the necessary
 * information for an order response, including the order ID, user ID, a list of
 * order items, total sum, order status, and order date.
 */
public class OrderResponseDTO {

	private Long id; // ID of the Order
	private Long userId; // ID of the user associated with the order
	private List<OrderItemResponseDTO> items; // List of products and quantities in the order with details
	private Double total; // Total sum of the items' subtotals
	private OrderStatus status; // Status of the order, e.g. PENDING, SHIPPED, DELIVERED
	private Instant orderDate; // Date and time when the order was placed

	public OrderResponseDTO() {
	}

	public OrderResponseDTO(Order order) {
		this.id = order.getId();
		this.userId = order.getUser().getId();
		this.items = order.getItems().stream().map(OrderItemResponseDTO::new).toList();
		this.total = order.getItems().stream().mapToDouble(x -> x.getSubtotal() * x.getQuantity()).sum();
		this.status = order.getStatus();
		this.orderDate = order.getOrderDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<OrderItemResponseDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemResponseDTO> items) {
		this.items = items;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Instant getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Instant orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, items, orderDate, status, total, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderResponseDTO other = (OrderResponseDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(items, other.items)
				&& Objects.equals(orderDate, other.orderDate) && status == other.status
				&& Objects.equals(total, other.total) && Objects.equals(userId, other.userId);
	}

}
