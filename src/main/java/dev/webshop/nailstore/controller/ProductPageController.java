package dev.webshop.nailstore.controller;

import dev.webshop.nailstore.model.Category;
import dev.webshop.nailstore.model.Product;
import dev.webshop.nailstore.repository.CategoryRepository;
import dev.webshop.nailstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/termekek-oldal")
public class ProductPageController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping
	public String showProductsPage(Model model) {
		List<Product> products = productRepository.findAll();
		List<Category> categories = categoryRepository.findAll();

		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
		return "products";
	}

	@GetMapping("/{slug}")
	public String showProduct(
			@PathVariable String slug, Model model) {
		try {
			Product product = productRepository.findBySlug(slug).orElseThrow();
			model.addAttribute("product", product);
			return "product-details";
		} catch (Exception e) {
			return "error";
		}
	}

	@GetMapping("/kategoriak/{slug}")
	public String showProductsByCategory(
			@PathVariable String slug, Model model
			) {

		Optional<Category> categoryOpt = categoryRepository.findBySlug(slug);
		if (categoryOpt.isPresent()) {
			Optional<List<Product>> products = productRepository.getProductsByCategory_Id(categoryOpt.get().getId());
			if (products.isPresent()) {
				model.addAttribute("products", products.get());
				model.addAttribute("categories", categoryRepository.findAll());
				return "products";
			}
		}
		return "error";
	}
}
