package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

@Controller
@RequestMapping({"/register"})
public class LoginRegisterController {

    private final UserService userService;

    public LoginRegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"", "/"})
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/proccess_registration")
    public String processRegistration(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPass = user.getPassword();
        user.setPassword(encoder.encode(rawPass));
        userService.save(1L, user);

        return "index";

    }


}
