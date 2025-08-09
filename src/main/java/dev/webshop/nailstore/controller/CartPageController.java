package dev.webshop.nailstore.controller;

import dev.webshop.nailstore.model.Cart;
import dev.webshop.nailstore.model.CartItem;
import dev.webshop.nailstore.model.Product;
import dev.webshop.nailstore.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kosar")
public class CartPageController {
	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public String showCartPage(HttpSession session, Model model) {
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			System.out.println("Nincs kosár a session-ben, újat készítek.");
			cart = new Cart();
			session.setAttribute("cart", cart);
		} else {
			System.out.println("Kosár megtalálva a session-ben, tételek száma: " + cart.getItems().size());
		}
		model.addAttribute("cart", cart);
		return "cart";
	}

	@PostMapping("/hozzaad/{productId}")
	public String addToCart(@PathVariable Long productId,
													Model model,
													@RequestParam(defaultValue = "1") int quantity,
													HttpSession session) {
		try {
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart == null) {
				cart = new Cart();
				session.setAttribute("cart", cart);
			}

			Product product = productRepository.findById(productId).orElseThrow();
			cart.addItem(product, quantity);
			model.addAttribute("cart", cart);
			model.addAttribute("totalPrice", cart.getTotal());
			return "cart";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "error";
		}
	}

	@PostMapping("/torol/{productId}")
	public String removeFromCart(@PathVariable Long productId,
															 Model model,
															 HttpSession session
	){
			try {
			Cart cart = (Cart) session.getAttribute("cart");
			if (cart != null) {
				cart.removeItem(productId);
				session.setAttribute("cart", cart);
				model.addAttribute("cart", cart);
				model.addAttribute("totalPrice", cart.getTotal());
				return "cart";
			} else {
				System.out.println("No products are in the cart");
			}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return "error";
	}

	@PostMapping("/modosit/{productId}")
	public String modifyCartItemQuantity(@PathVariable Long productId,
																			 @RequestParam int quantity,
																			 Model model,
																			 HttpSession session
	) {
		try {
			Cart cart = (Cart) session.getAttribute("cart");

			if (cart != null) {
				cart.modifyItemQuantity(productId, quantity);
				session.setAttribute("cart", cart);
				model.addAttribute("cart", cart);
				model.addAttribute("totalPrice", cart.getTotal());
				return "cart";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "error";
	}
}
