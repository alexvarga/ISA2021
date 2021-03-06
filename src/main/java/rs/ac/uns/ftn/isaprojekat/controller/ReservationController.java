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
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isaprojekat.model.*;
import rs.ac.uns.ftn.isaprojekat.service.AdventureReservationService;
import rs.ac.uns.ftn.isaprojekat.service.BoatReservationService;
import rs.ac.uns.ftn.isaprojekat.service.UserService;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseReservationService;

import java.time.LocalDateTime;
import java.util.List;


@RequestMapping("/reservations")
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




    @GetMapping({"/", ""})
    public String show(Model model){


        return showReservationsByPage(model, 1, 1, 1, "id", "id", "id", "asc", "asc", "asc");
    }

    @GetMapping("/page/{pageB}/{pageH}/{pageA}")
    public String showReservationsByPage(Model model,
                                         @PathVariable("pageB") int currentPageB,
                                         @PathVariable("pageH") int currentPageH,
                                         @PathVariable("pageA") int currentPageA,
                                         @Param(value="sortFieldA") String sortFieldA,
                                         @Param(value="sortFieldB") String sortFieldB,
                                         @Param(value="sortFieldH") String sortFieldH,
                                         @Param(value="sortDirectionA") String sortDirectionA,
                                         @Param(value="sortDirectionB") String sortDirectionB,
                                         @Param(value="sortDirectionH") String sortDirectionH){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        if (sortFieldA==null){sortFieldH="id";}
        if (sortFieldB==null){sortFieldH="id";}
        if (sortFieldH==null){sortFieldH="id";}
        if(sortDirectionA==null){sortDirectionA="asc";}
        if(sortDirectionB==null){sortDirectionH="asc";}
        if(sortDirectionH==null){sortDirectionH="asc";}

        Page<AdventureReservation> pageAdventure = adventureReservationService.getAllByUserAndDateEndAfterAndReservationTypeNotDiscount(user, LocalDateTime.now(), currentPageA, sortFieldA, sortDirectionA);
        Page<BoatReservation> pageBoat = boatReservationService.getAllByUserAndDateEndAfterAndReservationTypeNotDiscount(user, LocalDateTime.now(), currentPageB, sortFieldB, sortDirectionB);
        Page<VacationHouseReservation> pageHouse = vacationHouseReservationService.getAllByUserAndDateEndAfterAndReservationTypeNotDiscount(user, LocalDateTime.now(), currentPageH, sortFieldH, sortDirectionH);

        List<VacationHouseReservation> entitiesHouse = pageHouse.getContent();
        List<BoatReservation> entitiesBoat = pageBoat.getContent();

        List<AdventureReservation> entitiesAdventure = pageAdventure.getContent();




        Long numberOfElementsHouse = pageHouse.getTotalElements();
        int numberOfPagesHouse = pageHouse.getTotalPages();


        Long numberOfElementsBoat = pageBoat.getTotalElements();
        int numberOfPagesBoat = pageBoat.getTotalPages();

        Long numberOfElementsAdventure = pageAdventure.getTotalElements();
        int numberOfPagesAdventure = pageAdventure.getTotalPages();

        model.addAttribute("currentPageB", currentPageB);
        model.addAttribute("currentPageA", currentPageA);
        model.addAttribute("currentPageH", currentPageH);
        model.addAttribute("numberOfElementsHouse", numberOfElementsHouse);
        model.addAttribute("numberOfElementsAdventure", numberOfElementsAdventure);
        model.addAttribute("numberOfElementsBoat", numberOfElementsBoat);
        model.addAttribute("numberOfPagesHouse", numberOfPagesHouse);
        model.addAttribute("numberOfPagesBoat", numberOfPagesBoat);
        model.addAttribute("numberOfPagesAdventure", numberOfPagesAdventure);
        if(!entitiesHouse.isEmpty()){         model.addAttribute("vacationHouseReservations", entitiesHouse);
            model.addAttribute("vacationHouseHistory", false);
        }

        if(!entitiesBoat.isEmpty()){         model.addAttribute("boatReservations", entitiesBoat);
            model.addAttribute("boatHistory", false);
        }

        if(!entitiesAdventure.isEmpty()){         model.addAttribute("adventureReservations", entitiesAdventure);
            model.addAttribute("adventureHistory", false);
        }

        model.addAttribute("sortFieldA", sortFieldA);
        model.addAttribute("sortFieldB", sortFieldB);
        model.addAttribute("sortFieldH", sortFieldH);

        model.addAttribute("sortDirectionA", sortDirectionA);
        model.addAttribute("sortDirectionB", sortDirectionB);
        model.addAttribute("sortDirectionH", sortDirectionH);

        String reverseSortDirectionA = sortDirectionA.equals("asc") ? "desc" : "asc";
        String reverseSortDirectionB = sortDirectionB.equals("asc") ? "desc" : "asc";
        String reverseSortDirectionH = sortDirectionH.equals("asc") ? "desc" : "asc";

        model.addAttribute("reverseSortDirectionA", reverseSortDirectionA);
        model.addAttribute("reverseSortDirectionB", reverseSortDirectionB);
        model.addAttribute("reverseSortDirectionH", reverseSortDirectionH);

        return "reservations";
    }


   @PostMapping("/cancel/boat")
   public String cancelBoatReservation(Model model, @Param(value="boatentityId") Long boatentityId){

       BoatReservation boatReservation = boatReservationService.findById(boatentityId);
       boatReservation.setReservationType(ReservationType.CANCELLED);
       boatReservationService.save(1L, boatReservation);
       return show(model);
    }

    @PostMapping("/cancel/adventure")
    public String cancelAdventureReservation(Model model, @Param(value="adventureentityId") Long adventureentityId){

        AdventureReservation adventureReservation = adventureReservationService.findById(adventureentityId);
        adventureReservation.setReservationType(ReservationType.CANCELLED);
        adventureReservationService.save(1L, adventureReservation);
        return show(model);
    }

    @PostMapping("/cancel/house")
    public String cancelVacationHouseReservation(Model model,  @Param(value="houseentityId") Long houseentityId){
        VacationHouseReservation vacationHouseReservation = vacationHouseReservationService.findById(houseentityId);
        vacationHouseReservation.setReservationType(ReservationType.CANCELLED);
        vacationHouseReservationService.save(1L, vacationHouseReservation);

        return show(model);
    }




}
