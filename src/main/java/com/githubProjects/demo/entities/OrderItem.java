package com.githubProjects.demo.entities;

import com.githubProjects.demo.entities.pk.OrderItemPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem {

	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();

	private Integer quantity;
	private Double subtotal;

	public OrderItem() {
	}

	public OrderItem(Order order, Product product, Integer quantity, Double subtotal) {
		this.id.setOrder(order);
		this.id.setProduct(product);
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	@ManyToOne
	@JoinColumn(name = "order_id", insertable = false, updatable = false)
	public Order getOrder() {
		return id.getOrder();
	}

	public void setOrder(Order order) {
		id.setOrder(order);
	}

	@ManyToOne
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	public Product getProduct() {
		return id.getProduct();
	}

	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
}
