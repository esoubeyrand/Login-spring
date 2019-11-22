package fr.gtm.login;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemoController {
	@Autowired
	ClientRepository clientRepository;

	Digest digest;

	@GetMapping("/")
	public String hello(@RequestParam(name = "name", defaultValue = "Jean Neige", required = false) String name,
			Model model) {
		String message = "Tu ne sais rien " + name;
		model.addAttribute("message", message);
		return "home";

	}

	@GetMapping("/login")
	public String login(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}

	@PostMapping("/connexion")
	public String connexion(@RequestParam("password") String password, User user, Model model)
			throws NoSuchAlgorithmException {
		String hash = digest.encode(password);
		model.addAttribute("user", user);
		String nom = user.getNom();
		User user2 = clientRepository.getByNom(nom);
		if (user2 != null) {
			if (clientRepository.getDigestByNom(nom).equals(hash)) {
				return "ok";
			} else {
				return "non";
			}
		} else {
			return "non";
		}
	}
}
