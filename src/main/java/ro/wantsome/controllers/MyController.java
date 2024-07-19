package ro.wantsome.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

	@GetMapping("/users")
	public String getUsers() {
			System.out.println("Getting users");

			return "users";
		}

	@PostMapping("/submitForm")
	public String postForm(@RequestParam("name") String name,
			@RequestParam("email") String email){
		System.out.println("Name: " + name);
		System.out.println("Email: " + email);

		return "success";
	}
}
