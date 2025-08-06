package com.productapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productapp.dto.ProductDto;
import com.productapp.model.Product;
import com.productapp.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAll() {
		return productRepository.findAll();
	}

	public Optional<Product> getProductById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		return product;
	}

	public Product saveProduct(Product p) {
		return productRepository.save(p);

	}

	public String deleteProduct(Long id) {
		productRepository.deleteById(id);
		return "delete successfull";
	}

	public Product updateProduct(Product p) {
		return productRepository.save(p);

	}
}
