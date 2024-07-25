package pr6.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pr6.model.Profile;
import pr6.service.ProfileService;

@Controller
@RequestMapping("/")
public class LoginController {

    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(ProfileService profileService, PasswordEncoder passwordEncoder) {
        this.profileService = profileService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("usernameError", true);
        }
        return "login";
    }


    @GetMapping("/register")
    public String registration(@ModelAttribute("profile") Profile shopUser) {
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("profile") @Valid Profile account, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors("username") || bindingResult.hasFieldErrors("password")) {
            return "register";
        }
        profileService.registerUser(account.getUsername(), account.getPassword());
        return "redirect:/bags";
    }


}
