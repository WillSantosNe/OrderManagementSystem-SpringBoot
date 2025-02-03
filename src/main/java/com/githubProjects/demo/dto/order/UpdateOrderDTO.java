package com.githubProjects.demo.dto.order;

import java.util.List;
import java.util.Objects;

import com.githubProjects.demo.dto.orderItem.OrderItemDTO;
import com.githubProjects.demo.entities.Order;
import com.githubProjects.demo.entities.OrderStatus;

/**
 * Data Transfer Object (DTO) for updating an order. Encapsulates the necessary
 * information for updating an order, including order items and order status.
 */
public class UpdateOrderDTO {

	private List<OrderItemDTO> items; // List of products and quantities in the order
    private OrderStatus status; // Updated status of the order

    /**
     * Default constructor.
     */
    public UpdateOrderDTO() {}

    /**
     * Constructs an UpdateOrderDTO from an existing Order entity.
     *
     * @param order The order entity used to populate this DTO.
     */
    public UpdateOrderDTO(Order order) {
        this.items = order.getItems().stream().map(OrderItemDTO::new).toList();
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
