package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.service.BoatReservationService;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

import java.time.LocalDateTime;

@Profile("default")
@Controller
public class BoatReservationHistoryController {

    private final BoatReservationService boatReservationService;
    private final UserService userService;

    public BoatReservationHistoryController(BoatReservationService boatReservationService, UserService userService) {
        this.boatReservationService = boatReservationService;
        this.userService = userService;
    }

    @GetMapping("/boatHistory")
    public String listBoats(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);


        model.addAttribute("boatReservations", boatReservationService.getAllByUserAndDateEndBefore(user, LocalDateTime.now()));
        return "reservations";
    }

}
