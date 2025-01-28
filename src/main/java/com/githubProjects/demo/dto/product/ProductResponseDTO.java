package com.githubProjects.demo.dto.product;

import com.githubProjects.demo.entities.Product;

/*DTO to bring product data*/
public class ProductResponseDTO {
	private Long id;
	private String name;
	private String description;
	private Double price;

	public ProductResponseDTO() {

	}
	
	public ProductResponseDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
