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
import rs.ac.uns.ftn.isaprojekat.model.ReservationType;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseReservation;
import rs.ac.uns.ftn.isaprojekat.service.UserService;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseReservationService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class VacationHouseDiscountOfferController {

    private final VacationHouseReservationService vacationHouseReservationService;
    private final UserService userService;


    public VacationHouseDiscountOfferController(VacationHouseReservationService vacationHouseReservationService, UserService userService) {
        this.vacationHouseReservationService = vacationHouseReservationService;
        this.userService = userService;
    }


    @GetMapping("/houses/discount")
    public String showOffers(Model model) {

        return showOffersByPage(model, 1, "id", "asc");
    }

    @GetMapping({"/houses/discount/page/{pageNumber}"})
    public String showOffersByPage(Model model,
                                   @PathVariable("pageNumber") int currentPage,
                                   @Param(value="sortField") String sortField,
                                   @Param(value="sortDirection") String sortDirection){

        if (sortField==null){sortField="id";}
        if(sortDirection==null){sortDirection="asc";}
        Page<VacationHouseReservation> page = vacationHouseReservationService.getAllByReservationType(ReservationType.DISCOUNTOFFER, currentPage, sortField, sortDirection);
        List<VacationHouseReservation> entities = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        if(!entities.isEmpty()){         model.addAttribute("vacationHouseDiscountOffers", entities);}

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);

        return "house_discount";

    }

    @PostMapping({"/houses/discount/reserve"})
    public String makeAReservation(Model model, @Param(value = "offerId") Long offerId ) throws UnsupportedEncodingException, MessagingException {

        VacationHouseReservation vacationHouseReservation = vacationHouseReservationService.findById(offerId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        vacationHouseReservation.setUser(user);
        vacationHouseReservation.setReservationType(ReservationType.ACTIVE);
        vacationHouseReservation.setReservationTime(LocalDateTime.now());
        vacationHouseReservationService.save(1L, vacationHouseReservation);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        userService.sendReservationConfirmationEmail(vacationHouseReservation.getVacationHouse().getName(),
                "vikendicu", vacationHouseReservation.getDateFrom().format(formatter), vacationHouseReservation.getDateEnd().format(formatter),
                vacationHouseReservation.getVacationHouse().getAddress(), email );


        //TODO mail confirmation

        return "index";

    }


}
