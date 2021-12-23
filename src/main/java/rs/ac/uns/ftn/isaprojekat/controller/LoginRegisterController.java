package rs.ac.uns.ftn.isaprojekat.controller;

import net.bytebuddy.utility.RandomString;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isaprojekat.MyAppUrl;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.UserRole;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping({"/register"})
public class LoginRegisterController {

    private final UserService userService;

    public LoginRegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/"})
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register/register";
    }

/*    @PostMapping("/proccess_registration")
    public String processRegistration(User user, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPass = user.getPassword();
        user.setPassword(encoder.encode(rawPass));

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);

        user.setUserRole(UserRole.USER);
        user.setEnabled(false);
        user.setLocked(false);


        userService.save(1L, user); //id here does nothing todo


        String siteUrl = MyAppUrl.getSiteUrl(request);
        userService.sendVerificationEmail(user, siteUrl);

        return "register/register_verify_sent";

    }*/


    @PostMapping("/")
    public String process(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, HttpServletRequest request,
                          @RequestParam(value = "password-confirm", required = false) String passwordConfirm) throws UnsupportedEncodingException, MessagingException {

        if(!user.getPassword().equals(passwordConfirm)){
            bindingResult.addError(new FieldError("user", "password", "Lozinke se ne poklapaju."));
        }

        if(bindingResult.hasErrors()){



            return ("register/register");
        }else {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String rawPass = user.getPassword();
            user.setPassword(encoder.encode(rawPass));

            String randomCode = RandomString.make(64);
            user.setVerificationCode(randomCode);

            user.setUserRole(UserRole.USER);
            user.setEnabled(false);
            user.setLocked(false);


            userService.save(1L, user); //id here does nothing todo


            String siteUrl = MyAppUrl.getSiteUrl(request);
            userService.sendVerificationEmail(user, siteUrl);

            return "register/register_verify_sent";
        }

    }

    @GetMapping("/verify")
    public String verifyAccount(@Param("code") String code, Model model){
        boolean verified = userService.verifyUser(code);

        return verified ? "register/validation_success" : "register/validation_fail";


    }


}
