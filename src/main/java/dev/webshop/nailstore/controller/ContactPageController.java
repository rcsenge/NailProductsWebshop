package dev.webshop.nailstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactPageController {

	@GetMapping("/kapcsolat")
	public String showContactPage() {
		return "contact";
	}
}
