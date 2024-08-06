package ro.wantsome.security.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.wantsome.security.domain.User;
import ro.wantsome.security.domain.UserJpaRepository;

@Controller
public class RegisterController {

    private final PasswordEncoder passwordEncoder;
    private final UserJpaRepository userRepository;

    public RegisterController(PasswordEncoder passwordEncoder,
                              UserJpaRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("user", new User());

        return "security/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("FINANCIAL");
        userRepository.save(user);
        return "redirect:/login";
    }

}
