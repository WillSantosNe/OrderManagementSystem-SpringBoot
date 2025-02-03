package com.githubProjects.demo.dto.order;

import java.util.List;
import java.util.Objects;

import com.githubProjects.demo.dto.orderItem.OrderItemDTO;
import com.githubProjects.demo.entities.Order;

/**
 * Data Transfer Object (DTO) for creating a new order. Encapsulates the
 * necessary information to create an order, including the user ID and a list of
 * order items.
 */
public class CreateOrderDTO {

	private Long userId; // ID of the user associated with the order
	private List<OrderItemDTO> items; // List of products and quantities in the order

	/**
	 * Default constructor.
	 */
	public CreateOrderDTO() {
	}

	/**
	 * Constructs a CreateOrderDTO from an existing Order entity.
	 *
	 * @param order The order entity used to populate this DTO.
	 */
	public CreateOrderDTO(Order order) {
		this.userId = order.getUser().getId();
		this.items = order.getItems().stream().map(OrderItemDTO::new).toList();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(items, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateOrderDTO other = (CreateOrderDTO) obj;
		return Objects.equals(items, other.items) && Objects.equals(userId, other.userId);
	}

}
