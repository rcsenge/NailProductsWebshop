package dev.webshop.nailstore.controller;

import dev.webshop.nailstore.model.ContactMessage;
import dev.webshop.nailstore.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/kapcsolat")
public class ContactPageController {
	@Autowired
	ContactMessageRepository contactMessageRepository;

	@GetMapping
	public String showContactPage() {
		return "contact";
	}

	@PostMapping("/kuldes")
	public String submitContact(
			@RequestParam String name,
			@RequestParam String email,
			@RequestParam String message
	) {
		if (!name.isEmpty() && !email.isEmpty() && !message.isEmpty()) {
			ContactMessage contactMessage = new ContactMessage(name, email, message);
			contactMessageRepository.save(contactMessage);
			return "contact";
		} else {
			return "error";
		}
	}
}
