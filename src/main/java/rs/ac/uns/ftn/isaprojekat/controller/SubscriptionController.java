package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rs.ac.uns.ftn.isaprojekat.model.BoatSubscription;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.service.BoatService;
import rs.ac.uns.ftn.isaprojekat.service.BoatSubscriptionService;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

import java.util.Set;

@Controller
public class SubscriptionController {

    private final UserService userService;
    private final BoatSubscriptionService boatSubscriptionService;

    private final BoatService boatService;

    public SubscriptionController(UserService userService, BoatSubscriptionService boatSubscriptionService, BoatService boatService) {
        this.userService = userService;
        this.boatSubscriptionService = boatSubscriptionService;
        this.boatService = boatService;
    }

    @GetMapping("/subscriptions")
    String showAllSubscriptions(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        Set<BoatSubscription> boatSubscriptions = boatSubscriptionService.findAllByUser(user);

        model.addAttribute("boatSubscriptions", boatSubscriptions);

        return "subscriptions";
    }

    @PostMapping("/subscriptions")
    String unsubscribe(Model model, @RequestParam(value="entityType") String entityType,
                       @RequestParam(value = "id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        System.out.println(entityType + " "+ id);

        switch (entityType){
            case "boat": ;
                boatSubscriptionService.deleteById(id);
                break;
            case "house":
                break;
            case "instructor":
                break;
        }

        return showAllSubscriptions(model);
    }
}
