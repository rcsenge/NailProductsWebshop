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
@RequestMapping("/")
public class LandingPageController {
	@Autowired
	ProductRepository productRepository;

	@GetMapping
	public String landingPage(Model model) {
		// Temporary until order management is done
		List<Product> bestSellers = productRepository.findAll().subList(0,3);

		model.addAttribute("bestSellers", bestSellers);
		return "index";
	}
}
