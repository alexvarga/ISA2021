package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isaprojekat.model.*;
import rs.ac.uns.ftn.isaprojekat.service.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping({""})
@Controller
public class BoatController {

    private final BoatService boatService;
    private final BoatReviewService boatReviewService;
    private final UserService userService;
    private final BoatSubscriptionService boatSubscriptionService;
    private final BoatReservationService boatReservationService;
    private final BoatComplaintService boatComplaintService;

    public BoatController(BoatService boatService, BoatReviewService boatReviewService, UserService userService, BoatSubscriptionService boatSubscriptionService, BoatReservationService boatReservationService, BoatComplaintService boatComplaintService) {
        this.boatService = boatService;
        this.boatReviewService = boatReviewService;
        this.userService = userService;
        this.boatSubscriptionService = boatSubscriptionService;
        this.boatReservationService = boatReservationService;
        this.boatComplaintService = boatComplaintService;
    }

    @GetMapping({"/boats", "/boats/"})
    public String listBoats(Model model){
        return listBoatsByPage(model, 1, "id", "asc");
    }

    @GetMapping({"/boats/page/{pageNumber}"})
    public String listBoatsByPage(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @Param(value="sortField") String sortField,
                                  @Param(value="sortDirection") String sortDirection){


       // currentPage = 1;
        System.out.println(sortField);
        if (sortField==null){sortField="id";}
        if(sortDirection==null){sortDirection="asc";}
        Page<Boat> page = boatService.findAll(currentPage, sortField, sortDirection);
        List<Boat> listBoats = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("boats", listBoats);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);

        return "boats";
    }


    @RequestMapping(value = "/boats/{id}", method = GET)
    public String showBoat(Model model, @PathVariable("id") long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        Boat boat = boatService.findById(id);
        Boolean allowComplaint = boatReservationService.existsByUserAndBoat(user, boat);
        System.out.println(allowComplaint+"allow complaint");
        Set<BoatReview> boatReviews = boatReviewService.getAllByBoatAndReviewStatus(boat, ReviewStatus.ALLOWED);

        Boolean subscribed = boatSubscriptionService.existsByUserAndBoat(user, boat);


        model.addAttribute("allowComplaint", allowComplaint);
        model.addAttribute("boatReviews", boatReviews);
        model.addAttribute("boat", boat);
        model.addAttribute("subscribed", subscribed);

        return "boat";
    }

    @PostMapping("/search/boats")
    String listBoatResults(Model model,
                           @ModelAttribute(value = "dateFrom") String dateFrom,
                           @ModelAttribute(value = "dateEnd") String dateEnd,
                           @ModelAttribute(value = "tag1") String tag1,
                           @ModelAttribute(value = "tag2") String tag2,
                           @ModelAttribute(value = "tag3") String tag3,
                           @ModelAttribute(value = "maxPrice") Float maxPrice,
                           @ModelAttribute(value = "minRating") Float minRating,
                           @ModelAttribute(value = "noOfPersons") Integer noOfPersons) {


        return listBoatResultsByPage(model, 1, "id", "asc", dateFrom, dateEnd, tag1, tag2, tag3, maxPrice, minRating, noOfPersons );



    }

    @GetMapping("/search/boats/page/{pageNumber}")
    String listBoatResultsByPage(Model model, @PathVariable("pageNumber") int currentPage,
                                 @Param(value="sortField") String sortField,
                                 @Param(value="sortDirection") String sortDirection,
                                 @Param(value="dateFrom") String dateFrom,
                                 @Param(value="dateEnd") String dateEnd,
                                 @Param(value = "tag1") String tag1,
                                 @Param(value = "tag2") String tag2,
                                 @Param(value = "tag3") String tag3,
                                 @Param(value = "maxPrice") Float maxPrice,
                                 @Param(value = "minRating") Float minRating,
                                 @Param(value = "noOfPersons") Integer noOfPersons){

        if (sortField==null){sortField="id";}
        if(sortDirection==null){sortDirection="asc";}

        System.out.println(tag1+" " +tag2+ " "+tag3 + " ");
        System.out.println(noOfPersons);
        System.out.println(maxPrice);
        System.out.println(minRating);

       // System.out.println(check.isEmpty());

        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateEnd", dateEnd);
        System.out.println(dateFrom + "+++" + dateEnd);


        Page<Boat> page = boatService.findBoatsNotReserved(currentPage,
                sortField,
                sortDirection,
                LocalDateTime.parse(dateFrom+" 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse(dateEnd+" 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                maxPrice,
                minRating,
                noOfPersons,
                tag1, tag2, tag3);
        List<Boat> listBoats = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        model.addAttribute("search", true);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("boats", listBoats);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("minRating", minRating);
        model.addAttribute("noOfPersons", noOfPersons);
        model.addAttribute("tag1", tag1);
        model.addAttribute("tag2", tag2);
        model.addAttribute("tag3", tag3);


        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);



        return "boats";
    }

    @PostMapping("/boat/rate")
    String rateBoat(Model model, @Param(value = "boatIdRate") Long boatIdRate,
                    @Param(value = "content") String content, @Param(value="boatratingValue") Float boatratingValue,  Principal principal){

        BoatReview review = new BoatReview();
        User dbUser = userService.findByEmail(principal.getName());

        review.setBoat(boatService.findById(boatIdRate));
        review.setUser(dbUser);
        review.setContent(content);
        review.setRating(boatratingValue);
        review.setReviewTime(LocalDateTime.now());
        review.setReviewStatus(ReviewStatus.PENDING);
        boatReviewService.save(1L, review);
        return "/userHomePage";
    }



    @PostMapping("/boats/{boatId}")
    String subscribe(Model model, @PathVariable @RequestParam("boatId") Long boatId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        Boat boat = boatService.findById(boatId);

        BoatSubscription subscription = new BoatSubscription();
        subscription.setBoat(boat);
        subscription.setUser(user);
        subscription.setDateOfSubscribing(LocalDate.now());
        boatSubscriptionService.save(1L, subscription);

        return showBoat(model, boatId);
    }

    @PutMapping("/boats/{boatId}")
    String unsubscribe(Model model, @PathVariable @RequestParam("boatId") Long boatId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        Boat boat = boatService.findById(boatId);

        boatSubscriptionService.deleteById(boatSubscriptionService.findBoatSubscriptionByBoatAndUser(boat, user).getId());

        return showBoat(model, boatId);
    }


    @PostMapping("/boats/{boatId}/complaint")
    String makeComplaint(Model model, @PathVariable @RequestParam("boatId") Long boatId, @RequestParam("complaintContent") String complaintContent){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        BoatComplaint complaint = new BoatComplaint();
        complaint.setBoat(boatService.findById(boatId));
        complaint.setUser(user);
        complaint.setComplaintDate(LocalDate.now());
        complaint.setContent(complaintContent);
        boatComplaintService.save(1L, complaint);

        return "index";
    }

}
