package ro.wantsome.layered.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ro.wantsome.layered.service.UsersService;

@Controller
public class UsersController {

	private final UsersService usersService;

	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}

	@GetMapping("/userLis")
	public String getUsers(Model model){
		model.addAttribute("users", usersService.listUsers());
		return "users";
	}
}
