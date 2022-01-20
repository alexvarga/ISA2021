package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rs.ac.uns.ftn.isaprojekat.model.DeletionRequest;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.service.DeletionRequestService;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class ProfileController {

    private final UserService userService;
    private final DeletionRequestService deletionRequestService;

    public ProfileController(UserService userService, DeletionRequestService deletionRequestService) {
        this.userService = userService;
        this.deletionRequestService = deletionRequestService;
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
    public String update(User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "profile";
        }else {
            User dbUser = userService.findByEmail(user.getEmail());
            if (dbUser.getFirstName() != user.getFirstName()) {
                dbUser.setFirstName(user.getFirstName());
            }
            if (dbUser.getLastName() != user.getLastName()) {
                dbUser.setLastName(user.getLastName());
            }
            if (dbUser.getAddress() != user.getAddress()) {
                dbUser.setAddress(user.getAddress());
            }
            if (dbUser.getCity() != user.getCity()) {
                dbUser.setCity(user.getCity());
            }
            if (dbUser.getState() != user.getState()) {
                dbUser.setState(user.getState());
            }
            if (dbUser.getPhoneNumber() != user.getPhoneNumber()) {
                dbUser.setPhoneNumber(user.getPhoneNumber());
            }


            userService.save(dbUser.getId(), dbUser);

            return "/profile";
        }
    }

    @PostMapping("/profile/password")
    public String updateUser(User user, BindingResult bindingResult, Model model,
                             @RequestParam(value="password-confirm", required = false) String passwordConfirm,
                             @RequestParam(value="new-password", required = false) String passwordNew,
                             Principal principal){


        User dbUser = userService.findByEmail(principal.getName());
        System.out.println(dbUser);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


        //System.out.println(dbUser.getPassword()); if(!dbUser.getPassword().equals(encoder.encode(user.getPassword()))){

        if(!encoder.matches(user.getPassword(), dbUser.getPassword())){
            bindingResult.addError(new FieldError("user", "password", "Uneta lozinka nije ispravna."));
            System.out.println("uneta neispravna lozinka");
        }
        if(!passwordNew.equals(passwordConfirm)){
            bindingResult.addError(new FieldError("user", "password", "Lozinke se ne poklapaju."));
        }

        if(bindingResult.hasErrors()){
            System.out.println("imam greske ovde ");
            return "profile";
        }else{
            dbUser.setPassword(encoder.encode(passwordNew));
            userService.save(1L, dbUser);
        }


        return "/profile";
    }

    @PostMapping("/profile/request")
    String sendDeletionRequest(Model model, @RequestParam(value = "content") String content){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        DeletionRequest deletionRequest = new DeletionRequest();
        deletionRequest.setUser(user);
        deletionRequest.setDateOfRequest(LocalDate.now());
        deletionRequest.setText(content);
        deletionRequestService.save(1L, deletionRequest);

        return show(model);
    }


}

