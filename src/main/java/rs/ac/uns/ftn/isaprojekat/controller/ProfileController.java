package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

@Controller
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/profile", "/profile/"})
    public String show(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("user", user);

        return "profile";
    }

    @PostMapping("/profile/")
    public String update(User user){
        User dbUser = userService.findByEmail(user.getEmail());
        if(dbUser.getFirstName()!=user.getFirstName()){
            dbUser.setFirstName(user.getFirstName());
        }
        if(dbUser.getLastName()!=user.getLastName()){
            dbUser.setLastName(user.getLastName());
        }
        if(dbUser.getAddress()!=user.getAddress()){
            dbUser.setAddress(user.getAddress());
        }
        if(dbUser.getCity()!=user.getCity()){
            dbUser.setCity(user.getCity());
        }
        if(dbUser.getState()!=user.getState()){
            dbUser.setState(user.getState());
        }
        if(dbUser.getPhoneNumber()!=user.getPhoneNumber()){
            dbUser.setPhoneNumber(user.getPhoneNumber());
        }


        userService.save(dbUser.getId(), dbUser);

        return "/profile";
    }

    @PutMapping("/profile/")
    public String updateUser(User user){


        return "/profile";
    }


}

