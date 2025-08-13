package dev.webshop.nailstore.controller;

import dev.webshop.nailstore.model.Role;
import dev.webshop.nailstore.model.User;
import dev.webshop.nailstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	UserRepository userRepository;

	@GetMapping
	public String showSignupPage(Model model) {
		return "signup";
	}

	@PostMapping("/regisztracio")
	public String sendSignupForm(
			@RequestParam String firstName,
			@RequestParam String lastName,
			@RequestParam String username,
			@RequestParam String email,
			@RequestParam String phoneNumber,
			@RequestParam String password
	) {
		Optional<List<String>> usernames = userRepository.getAllUsernames();
		if (usernames.isPresent() && usernames.get().contains(username)) {
			System.out.println("Ez a felhasználónév már létezik!");
		}

		password = BCrypt.hashpw(password, BCrypt.gensalt());
		userRepository.save(new User(firstName, lastName, email, username, phoneNumber, Role.user, password));
		return "signup";
	}
}
