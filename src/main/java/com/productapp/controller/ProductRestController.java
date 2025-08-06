package com.productapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productapp.dto.ProductDto;
import com.productapp.model.Product;
import com.productapp.service.ProductService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductRestController {

	@Autowired
	private ProductService service;

	// get the products

	@GetMapping
	public ResponseEntity<?> getAllProducts() {
		List<Product> products = service.getAll();
		return ResponseEntity.ok(products);
	}

	// get the product by id
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id) {
		Optional<Product> product = service.getProductById(id);
		return ResponseEntity.ok(product);
	}

	// add product
	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product savedProduct = service.saveProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
	}

	// update the product
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product p) {
		Optional<Product> existing = service.getProductById(id);
		if (existing.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		p.setId(id);
		Product updated = service.updateProduct(p);
		return ResponseEntity.ok(updated);
	}

	// delete product by id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		Optional<Product> existing = service.getProductById(id);
		if (existing.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product not found");
		}
		this.service.deleteProduct(id);
		return ResponseEntity.ok("Product deleted successfully");
	}
}
