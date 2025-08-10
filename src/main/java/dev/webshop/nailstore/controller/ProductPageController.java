package dev.webshop.nailstore.controller;

import dev.webshop.nailstore.model.Category;
import dev.webshop.nailstore.model.Product;
import dev.webshop.nailstore.model.Tag;
import dev.webshop.nailstore.repository.CategoryRepository;
import dev.webshop.nailstore.repository.ProductRepository;
import dev.webshop.nailstore.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/termekek-oldal")
public class ProductPageController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private TagRepository tagRepository;

	@GetMapping
	public String showProductsPage(
			Model model,
			@RequestParam(required = false) List<String> tags,
			@RequestParam(required = false, defaultValue = "nameAsc") String sortBy
	) {
		List<Product> products = productRepository.findAll();
		List<Category> categories = categoryRepository.findAll();

		products = filterAndSortProducts(products, tags, sortBy);

		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
		model.addAttribute("allTags", tagRepository.findAll());
		model.addAttribute("tags", tags);
		model.addAttribute("sortBy", sortBy);

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
			@PathVariable String slug,
			Model model,
			@RequestParam(required = false) List<String> tags,
			@RequestParam(required = false, defaultValue = "nameAsc") String sortBy
	) {

		Optional<Category> categoryOpt = categoryRepository.findBySlug(slug);
		List<Product> products = categoryOpt
				.map(cat -> productRepository.getProductsByCategory_Id(cat.getId()).orElse(Collections.emptyList()))
				.orElseGet(Collections::emptyList);

		products = filterAndSortProducts(products, tags, sortBy);
		model.addAttribute("products", products);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("allTags", tagRepository.findAll());
		model.addAttribute("tags", tags);
		model.addAttribute("sortBy", sortBy);

		return "products";
	}

	private List<Product> filterAndSortProducts(
			List<Product> products,
			List<String> tags,
			String sortBy
	) {

		if (tags != null && !tags.isEmpty()) {
			Set<String> tagSet = new HashSet<>(tags);
			products = products.stream()
					.filter(p -> p.getTags().stream()
							.map(Tag::getName)
							.collect(Collectors.toSet())
							.containsAll(tagSet))
					.collect(Collectors.toList());
		}

		switch (sortBy) {
			case "priceAsc":
				products.sort(Comparator.comparing(Product::getPrice));
				break;
			case "priceDesc":
				products.sort(Comparator.comparing(Product::getPrice).reversed());
				break;
			case "nameDesc":
				products.sort(Comparator.comparing(Product::getName).reversed());
				break;
			default: // nameAsc
				products.sort(Comparator.comparing(Product::getName));
				break;
		}

		return products;
	}

	@GetMapping("/kereses")
	public String searchProducts(
			@RequestParam(name = "search", required = false) String search,
			Model model,
			@RequestParam(required = false) List<String> tags,
			@RequestParam(required = false, defaultValue = "nameAsc") String sortBy
	) {
		List<Product> searchResult;
		if (search != null && !search.isBlank()) {
			searchResult = productRepository.getProductsByNameContainingIgnoreCase(search);
		} else {
			searchResult = productRepository.findAll();
		}
		searchResult = filterAndSortProducts(searchResult, tags, sortBy);
		model.addAttribute("products", searchResult);
		model.addAttribute("search", search);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("allTags", tagRepository.findAll());
		model.addAttribute("tags", tags);
		model.addAttribute("sortBy", sortBy);

		return "products";
	}
}
