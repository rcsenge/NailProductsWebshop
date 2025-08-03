package dev.webshop.nailstore.controller;

import dev.webshop.nailstore.model.Product;
import dev.webshop.nailstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/termekek")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/kereses")
	public String searchProducts(
			@RequestParam(name = "search", required = false) String search,
			Model model
	) {
		List<Product> searchResult;
		if (search != null && !search.isBlank()) {
			searchResult = productRepository.getProductsByNameContainingIgnoreCase(search);
		} else {
			searchResult = productRepository.findAll();
		}
		model.addAttribute("products", searchResult);
		model.addAttribute("search", search);
		return "products";
	}
}
