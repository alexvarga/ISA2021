package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rs.ac.uns.ftn.isaprojekat.model.BoatSubscription;
import rs.ac.uns.ftn.isaprojekat.model.InstructorSubscription;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseSubscription;
import rs.ac.uns.ftn.isaprojekat.service.*;

import java.util.Set;

@Controller
public class SubscriptionController {

    private final UserService userService;

    private final BoatSubscriptionService boatSubscriptionService;
    private final VacationHouseSubscriptionService vacationHouseSubscriptionService;
    private final InstructorSubscriptionService instructorSubscriptionService;

    public SubscriptionController(UserService userService, BoatSubscriptionService boatSubscriptionService, BoatService boatService, VacationHouseSubscriptionService vacationHouseSubscriptionService, InstructorSubscriptionService instructorSubscriptionService) {
        this.userService = userService;
        this.boatSubscriptionService = boatSubscriptionService;
        this.instructorSubscriptionService = instructorSubscriptionService;
        this.vacationHouseSubscriptionService = vacationHouseSubscriptionService;
    }

    @GetMapping("/subscriptions")
    String showAllSubscriptions(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        Set<BoatSubscription> boatSubscriptions = boatSubscriptionService.findAllByUser(user);
        Set<VacationHouseSubscription> houseSubscriptions = vacationHouseSubscriptionService.findAllByUser(user);
        Set<InstructorSubscription> instructorSubscriptions = instructorSubscriptionService.findAllByUser(user);

        model.addAttribute("boatSubscriptions", boatSubscriptions);
        model.addAttribute("houseSubscriptions", houseSubscriptions);
        model.addAttribute("instructorSubscriptions", instructorSubscriptions);

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
            case "boat":
                boatSubscriptionService.deleteById(id);
                break;
            case "house":
                vacationHouseSubscriptionService.deleteById(id);
                break;
            case "instructor":
                instructorSubscriptionService.deleteById(id);
                break;
        }

        return showAllSubscriptions(model);
    }
}
