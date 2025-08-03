package dev.webshop.nailstore.controller;

import dev.webshop.nailstore.model.Product;
import dev.webshop.nailstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/termekek-oldal")
public class ProductPageController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public String showProductsPage(Model model) {
		List<Product> termekek = productRepository.findAll();
		model.addAttribute("products", termekek);
		return "products";
	}
}
