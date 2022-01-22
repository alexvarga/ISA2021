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
import rs.ac.uns.ftn.isaprojekat.model.BoatReservation;
import rs.ac.uns.ftn.isaprojekat.model.ReservationType;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.service.BoatReservationService;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class BoatDiscountOfferController {

    private final BoatReservationService boatReservationService;
    private final UserService userService;

    public BoatDiscountOfferController(BoatReservationService boatReservationService, UserService userService) {
        this.boatReservationService = boatReservationService;
        this.userService = userService;
    }

    @GetMapping("/boats/discount")
    public String showOffers(Model model) {

        return showOffersByPage(model, 1, "id", "asc");
    }

    @GetMapping({"/boats/discount/page/{pageNumber}"})
    public String showOffersByPage(Model model,
                                   @PathVariable("pageNumber") int currentPage,
                                   @Param(value="sortField") String sortField,
                                   @Param(value="sortDirection") String sortDirection){

        if (sortField==null){sortField="id";}
        if(sortDirection==null){sortDirection="asc";}
        Page<BoatReservation> page = boatReservationService.getAllByReservationType(ReservationType.DISCOUNTOFFER, currentPage, sortField, sortDirection);
        List<BoatReservation> entities = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        if(!entities.isEmpty()){         model.addAttribute("boatDiscountOffers", entities);}

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);

        return "boat_discount";

    }

    @PostMapping({"/boats/discount/reserve"})
    public String makeAReservation(Model model, @Param(value = "offerId") Long offerId ) throws UnsupportedEncodingException, MessagingException {

        BoatReservation boatReservation = boatReservationService.findById(offerId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        boatReservation.setUser(user);
        boatReservation.setReservationType(ReservationType.ACTIVE);
        boatReservation.setReservationTime(LocalDateTime.now());
        boatReservationService.save(1L, boatReservation);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        userService.sendReservationConfirmationEmail(boatReservation.getBoat().getName(),
                "brod", boatReservation.getDateFrom().format(formatter), boatReservation.getDateEnd().format(formatter),
                boatReservation.getBoat().getAddress(), email );

        return showOffers(model);

    }

}
