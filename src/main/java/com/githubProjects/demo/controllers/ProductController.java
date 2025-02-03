package com.githubProjects.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.githubProjects.demo.dto.product.CreateProductDTO;
import com.githubProjects.demo.dto.product.ProductResponseDTO;
import com.githubProjects.demo.dto.product.UpdateProductDTO;
import com.githubProjects.demo.services.ProductService;

/**
 * Controller responsible for managing products. Provides endpoints for
 * creating, retrieving, updating, and deleting products.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * Retrieves a product by its ID.
	 *
	 * @param id The ID of the product to retrieve.
	 * @return ResponseEntity containing the product details.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
		ProductResponseDTO product = productService.findById(id);
		return ResponseEntity.ok(product);
	}

	/**
	 * Retrieves all products.
	 *
	 * @return ResponseEntity containing a list of all products.
	 */
	@GetMapping
	public ResponseEntity<List<ProductResponseDTO>> findAll() {
		List<ProductResponseDTO> products = productService.findAll();
		return ResponseEntity.ok(products);
	}

	/**
	 * Creates a new product.
	 *
	 * @param dto The product data transfer object containing product details.
	 * @return ResponseEntity containing the created product details.
	 */
	@PostMapping
	public ResponseEntity<ProductResponseDTO> insert(@RequestBody CreateProductDTO dto) {
		ProductResponseDTO createdProduct = productService.insert(dto);
		return ResponseEntity.ok(createdProduct);
	}

	/**
	 * Updates an existing product.
	 *
	 * @param id  The ID of the product to update.
	 * @param dto The product update data transfer object containing updated
	 *            details.
	 * @return ResponseEntity containing the updated product details.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @RequestBody UpdateProductDTO dto) {
		ProductResponseDTO updatedProduct = productService.update(id, dto);
		return ResponseEntity.ok(updatedProduct);
	}

	/**
	 * Deletes a product by its ID.
	 *
	 * @param id The ID of the product to delete.
	 * @return ResponseEntity with no content if deletion is successful.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
