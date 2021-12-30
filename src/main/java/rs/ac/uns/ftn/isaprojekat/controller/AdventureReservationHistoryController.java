package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.service.AdventureReservationService;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

import java.time.LocalDateTime;

@Controller
public class AdventureReservationHistoryController {
    private final AdventureReservationService adventureReservationService;
    private final UserService userService;

    public AdventureReservationHistoryController(AdventureReservationService adventureReservationService, UserService userService) {
        this.adventureReservationService = adventureReservationService;
        this.userService = userService;
    }

    @GetMapping("/adventureHistory")
    public String listAdventures(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        model.addAttribute("adventureReservations", adventureReservationService.getAllByUserAndDateEndBefore(user, LocalDateTime.now()));
        return "reservations";
    }

}
