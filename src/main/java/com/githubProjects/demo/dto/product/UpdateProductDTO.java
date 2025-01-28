package com.githubProjects.demo.dto.product;

import com.githubProjects.demo.entities.Product;

/**
 * A DTO (Data Transfer Object) class for updating an existing product.
 */
public class UpdateProductDTO {
	private String name;
	private String description;
	private Double price;

	public UpdateProductDTO() {

	}

	public UpdateProductDTO(Product product) {
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
