package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import rs.ac.uns.ftn.isaprojekat.model.AdventureReservation;
import rs.ac.uns.ftn.isaprojekat.model.ReservationType;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.service.AdventureReservationService;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class AdventureDiscountOfferController {

    private final AdventureReservationService adventureReservationService;
    private final UserService userService;

    public AdventureDiscountOfferController(AdventureReservationService adventureReservationService, UserService userService) {
        this.adventureReservationService = adventureReservationService;
        this.userService = userService;
    }

    @GetMapping("/adventures/discount")
    public String showOffers(Model model) {

        return showOffersByPage(model, 1, "id", "asc");
    }

    @GetMapping({"/adventures/discount/page/{pageNumber}"})
    public String showOffersByPage(Model model,
                                   @PathVariable("pageNumber") int currentPage,
                                   @Param(value="sortField") String sortField,
                                   @Param(value="sortDirection") String sortDirection){

        if (sortField==null){sortField="id";}
        if(sortDirection==null){sortDirection="asc";}
        Page<AdventureReservation> page = adventureReservationService.getAllByReservationType(ReservationType.DISCOUNTOFFER, currentPage, sortField, sortDirection);
        List<AdventureReservation> entities = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        if(!entities.isEmpty()){         model.addAttribute("adventureDiscountOffers", entities);}

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);

        return "adventure_discount";

    }

    @PostMapping({"/adventures/discount/reserve"})
    public String makeAReservation(Model model, @Param(value = "offerId") Long offerId ) throws UnsupportedEncodingException, MessagingException {

        System.out.println(offerId);
        AdventureReservation adventureReservation = adventureReservationService.findById(offerId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        adventureReservation.setUser(user);
        adventureReservation.setReservationType(ReservationType.ACTIVE);
        adventureReservation.setReservationTime(LocalDateTime.now());
        adventureReservationService.save(1L, adventureReservation);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        userService.sendReservationConfirmationEmail(adventureReservation.getAdventure().getName(),
                "brod", adventureReservation.getDateFrom().format(formatter), adventureReservation.getDateEnd().format(formatter),
                adventureReservation.getAdventure().getAddress(), email );

        return "index";

    }

}
