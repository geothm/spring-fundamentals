package ro.wantsome.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting(Model model) {
		model.addAttribute("message", "Thymeleaf rocks!");
		model.addAttribute("items", List.of("Item1", "Item2", "Item3", "Item4"));

		return "greeting"; // Thymeleaf template name (greeting.html)
	}

}
