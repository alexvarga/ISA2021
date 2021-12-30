package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.service.UserService;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseReservationService;

import java.time.LocalDateTime;

@Controller
public class VacationHouseReservationHistoryController {

    private final VacationHouseReservationService vacationHouseReservationService;
    private final UserService userService;

    public VacationHouseReservationHistoryController(VacationHouseReservationService vacationHouseReservationService, UserService userService) {
        this.vacationHouseReservationService = vacationHouseReservationService;
        this.userService = userService;
    }

    @GetMapping("/houseHistory")
    public String listVacationHouses(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        model.addAttribute("vacationHouseReservations", vacationHouseReservationService.getAllByUserAndDateEndBefore(user, LocalDateTime.now()));
        return "reservations";
    }

}
