package com.productapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.productapp.model.Product;
import com.productapp.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("products", service.getAll());
		return "index";
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("product", new Product());
		return "add-product";
	}

	@PostMapping("/save")
	public String saveProduct(@ModelAttribute Product product) {
		service.saveProduct(product);
		return "redirect:/";
	}
}
