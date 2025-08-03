package dev.webshop.nailstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContactController {
	@PostMapping("/kapcsolat-kuldes")
	@ResponseBody
	public String handleContactForm(
			@RequestParam String name,
			@RequestParam String email,
			@RequestParam String message
	) {
		System.out.println("Új üzenet érkezett!");
		System.out.println("Név: " + name);
		System.out.println("E-mail: " + email);
		System.out.println("Üzenet: " + message);

		return "Köszönjük az üzeneted, " + name + "! Hamarosan válaszolunk.";
	}
}
