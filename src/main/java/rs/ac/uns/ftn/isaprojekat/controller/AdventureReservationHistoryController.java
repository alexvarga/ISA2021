package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isaprojekat.model.AdventureReservation;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.service.AdventureReservationService;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/adventureHistory")
@Controller
public class AdventureReservationHistoryController {
    private final AdventureReservationService adventureReservationService;
    private final UserService userService;

    public AdventureReservationHistoryController(AdventureReservationService adventureReservationService, UserService userService) {
        this.adventureReservationService = adventureReservationService;
        this.userService = userService;
    }

    @GetMapping({"/", ""})
    public String listAdventureHistory(Model model) {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        User user = userService.findByEmail(email);
//        Set<AdventureReservation> adventureReservations =
//                adventureReservationService.getAllByUserAndDateEndBefore(user, LocalDateTime.now());
//
//        if (!adventureReservations.isEmpty())
//            model.addAttribute("adventureReservations", adventureReservations);
        return listAdventureHistoryByPage(model, 1, "id", "asc");
    }

    @GetMapping({"/page/{pageNumber}"})
    public String listAdventureHistoryByPage(Model model,
                                             @PathVariable("pageNumber") int currentPage,
                                             @Param(value = "sortField") String sortField,
                                             @Param(value = "sortDirection") String sortDirection) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        if (sortField == null) {
            sortField = "id";
        }
        if (sortDirection == null) {
            sortDirection = "asc";
        }

        Page<AdventureReservation> page = adventureReservationService.getAllByUserAndDateEndBefore(user, LocalDateTime.now(), currentPage, sortField, sortDirection);
        List<AdventureReservation> entities = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages= page.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        if(!entities.isEmpty()){         model.addAttribute("adventureReservations", entities);
            model.addAttribute("adventureHistory", true);}
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);


        return "reservations";

    }
}


