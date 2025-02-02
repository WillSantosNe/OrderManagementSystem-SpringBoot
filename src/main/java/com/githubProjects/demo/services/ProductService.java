package com.githubProjects.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.githubProjects.demo.dto.product.CreateProductDTO;
import com.githubProjects.demo.dto.product.ProductResponseDTO;
import com.githubProjects.demo.dto.product.UpdateProductDTO;
import com.githubProjects.demo.entities.Product;
import com.githubProjects.demo.exceptions.ResourceNotFoundException;
import com.githubProjects.demo.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	/**
	 * Creates a new product in the database.
	 * 
	 * @param dto Data Transfer Object containing product details (name,
	 *            description, price).
	 * @return A ProductResponseDTO with the created product's information.
	 */
	public ProductResponseDTO insert(CreateProductDTO dto) {
		if (dto.getPrice() == null || dto.getPrice() < 0) {
			throw new IllegalArgumentException("Price must be a positive value.");
		}

		Product product = new Product();
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());

		Product savedProduct = productRepository.save(product);
		return new ProductResponseDTO(savedProduct);
	}

	/**
	 * Updates an existing product by ID.
	 * 
	 * @param id  The ID of the product to update.
	 * @param dto Data Transfer Object containing updated product details.
	 * @return A ProductResponseDTO with the updated product's information.
	 * @throws ResourceNotFoundException if the product with the specified ID is not
	 *                                   found.
	 */
	public ProductResponseDTO update(Long id, UpdateProductDTO dto) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));

		if (dto.getName() != null)
			product.setName(dto.getName());
		if (dto.getDescription() != null)
			product.setDescription(dto.getDescription());
		if (dto.getPrice() != null && dto.getPrice() >= 0) {
			product.setPrice(dto.getPrice());
		} else if (dto.getPrice() != null) {
			throw new IllegalArgumentException("Price must be a positive value.");
		}

		Product updatedProduct = productRepository.save(product);
		return new ProductResponseDTO(updatedProduct);
	}

	/**
	 * Retrieves a product by ID.
	 * 
	 * @param id The ID of the product to retrieve.
	 * @return A ProductResponseDTO with the product's information.
	 * @throws ResourceNotFoundException if the product with the specified ID is not
	 *                                   found.
	 */
	public ProductResponseDTO findById(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
		return new ProductResponseDTO(product);
	}

	/**
	 * Retrieves all products.
	 * 
	 * @return A list of ProductResponseDTOs containing all products.
	 */
	public List<ProductResponseDTO> findAll() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(ProductResponseDTO::new).collect(Collectors.toList());
	}

	/**
	 * Deletes a product by ID.
	 * 
	 * @param id The ID of the product to delete.
	 * @throws ResourceNotFoundException if the product with the specified ID does
	 *                                   not exist.
	 */
	public void delete(Long id) {
		if (!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("Product not found with ID: " + id);
		}
		productRepository.deleteById(id);
	}
}
