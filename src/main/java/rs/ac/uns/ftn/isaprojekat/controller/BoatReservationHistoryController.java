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
import rs.ac.uns.ftn.isaprojekat.model.BoatReservation;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.service.BoatReservationService;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping({"/boatHistory"})
@Controller
public class BoatReservationHistoryController {

    private final BoatReservationService boatReservationService;
    private final UserService userService;

    public BoatReservationHistoryController(BoatReservationService boatReservationService, UserService userService) {
        this.boatReservationService = boatReservationService;
        this.userService = userService;
    }

    @GetMapping({"/", ""})
    public String listBoatReservationHistory(Model model){
        return listBoatsHistoryByPage(model, 1, "id", "asc");
    }

    @GetMapping({"/page/{pageNumber}"})
    public String listBoatsHistoryByPage(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @Param(value="sortField") String sortField,
                                  @Param(value="sortDirection") String sortDirection){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        if (sortField==null){sortField="id";}
        if(sortDirection==null){sortDirection="asc";}
        Page<BoatReservation> page = boatReservationService.getAllByUserAndDateEndBeforeAndReservationTypeNotDiscount(user, LocalDateTime.now(), currentPage, sortField, sortDirection);
        List<BoatReservation> entities = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        if(!entities.isEmpty()){         model.addAttribute("boatReservations", entities);
            model.addAttribute("boatHistory", true);
        }
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);
        model.addAttribute("boatHistory", true);

        return "reservations";
    }

}
