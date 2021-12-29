package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rs.ac.uns.ftn.isaprojekat.model.AdventureReservation;
import rs.ac.uns.ftn.isaprojekat.model.BoatReservation;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseReservation;
import rs.ac.uns.ftn.isaprojekat.service.AdventureReservationService;
import rs.ac.uns.ftn.isaprojekat.service.BoatReservationService;
import rs.ac.uns.ftn.isaprojekat.service.UserService;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseReservationService;

import java.util.Set;

@Controller
public class ReservationController {

    private  final BoatReservationService boatReservationService;
    private  final AdventureReservationService adventureReservationService;
    private  final VacationHouseReservationService vacationHouseReservationService;
    private final UserService userService;

    public ReservationController(BoatReservationService boatReservationService, AdventureReservationService adventureReservationService, VacationHouseReservationService vacationHouseReservationService, UserService userService) {
        this.boatReservationService = boatReservationService;
        this.adventureReservationService = adventureReservationService;
        this.vacationHouseReservationService = vacationHouseReservationService;
        this.userService = userService;
    }




    @GetMapping("/reservations")
    String show(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        Set<BoatReservation> boatReservations = boatReservationService.getAllByUser(user);
        Set<AdventureReservation> adventureReservations = adventureReservationService.getAllByUser(user);
        Set<VacationHouseReservation> vacationHouseReservations = vacationHouseReservationService.getAllByUser(user);


        model.addAttribute("boatReservations", boatReservations);
        model.addAttribute("adventureReservations", adventureReservations);
        model.addAttribute("vacationHouseReservations", vacationHouseReservations);

        return "reservations";
    }

}
