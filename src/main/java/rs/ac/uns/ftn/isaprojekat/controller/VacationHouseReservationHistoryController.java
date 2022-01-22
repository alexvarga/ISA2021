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
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseReservation;
import rs.ac.uns.ftn.isaprojekat.service.UserService;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseReservationService;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/houseHistory")
@Controller
public class VacationHouseReservationHistoryController {

    private final VacationHouseReservationService vacationHouseReservationService;
    private final UserService userService;



    public VacationHouseReservationHistoryController(VacationHouseReservationService vacationHouseReservationService, UserService userService) {
        this.vacationHouseReservationService = vacationHouseReservationService;
        this.userService = userService;
    }

    @GetMapping({"/", ""})
    public String listVacationHouseHistory(Model model) {

        return listVacationHouseHistoryByPage(model, 1, "id", "asc");
    }

    @GetMapping({"/page/{pageNumber}"})
    public String listVacationHouseHistoryByPage(Model model,
                                                 @PathVariable("pageNumber") int currentPage,
                                                 @Param(value="sortField") String sortField,
                                                 @Param(value="sortDirection") String sortDirection){


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        if (sortField==null){sortField="id";}
        if(sortDirection==null){sortDirection="asc";}
        Page<VacationHouseReservation> page = vacationHouseReservationService.getAllByUserAndDateEndBeforeAndReservationTypeNotDiscount(user, LocalDateTime.now(), currentPage, sortField, sortDirection);
        List<VacationHouseReservation> entities = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        //Set<BoatReservation> boatReservations = boatReservationService.getAllByUserAndDateEndBefore(user, LocalDateTime.now());

        //if(!boatReservations.isEmpty())model.addAttribute("boatReservations", boatReservationService.getAllByUserAndDateEndBefore(user, LocalDateTime.now()));

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        if(!entities.isEmpty()){         model.addAttribute("vacationHouseReservations", entities);
            model.addAttribute("vacationHouseHistory", true);}
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);


        return "reservations";

    }

}
