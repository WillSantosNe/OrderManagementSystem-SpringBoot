package com.githubProjects.demo.dto.order;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

import com.githubProjects.demo.dto.orderItem.OrderItemResponseDTO;
import com.githubProjects.demo.entities.Order;
import com.githubProjects.demo.entities.OrderStatus;

/**
 * Data Transfer Object (DTO) for order responses. Encapsulates details of an
 * order, including ID, user ID, order items, total sum, status, and order date.
 */
public class OrderResponseDTO {

	private Long id; // ID of the order
	private Long userId; // ID of the user associated with the order
	private List<OrderItemResponseDTO> items; // List of products and quantities with details
	private Double total; // Total sum of the order
	private OrderStatus status; // Current status of the order
	private Instant orderDate; // Date and time when the order was placed

	/**
	 * Default constructor.
	 */
	public OrderResponseDTO() {
	}

	/**
	 * Constructs an OrderResponseDTO from an existing Order entity.
	 *
	 * @param order The order entity used to populate this DTO.
	 */
	public OrderResponseDTO(Order order) {
		this.id = order.getId();
		this.userId = order.getUser().getId();
		this.items = order.getItems().stream().map(OrderItemResponseDTO::new).toList();
		this.total = order.getItems().stream().mapToDouble(x -> x.getSubtotal()).sum();
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
