package ro.wantsome.layered.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.wantsome.layered.domain.UserValidation;
import ro.wantsome.layered.domain.UsersRepository;
import ro.wantsome.layered.service.UsersService;

@Controller
public class UsersController {

	private final UsersService usersService;
	private final UsersRepository usersRepository;

	public UsersController(UsersService usersService, UsersRepository usersRepository) {
		this.usersService = usersService;
		this.usersRepository = usersRepository;
	}

	@GetMapping("/userList")
	public String getUsers(Model model){
		model.addAttribute("users", usersService.listUsers());
		return "users";
	}

	@GetMapping("/userForm")
	public String userForm(Model model) {
		model.addAttribute("user", new UserValidation());
		return "userForm";
	}


	@PostMapping("/submitUser")
	public String submitUser(@Valid @ModelAttribute UserValidation userValidation,
			BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			return "userForm";
		}

		//save user
		usersRepository.save(userValidation);

		model.addAttribute("user", userValidation);
		return "result";
	}

}
