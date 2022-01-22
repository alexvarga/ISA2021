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

//@RequestMapping({"/adventures"})
@Controller
public class AdventureController {

    private final AdventureService adventureService;
    private final UserService userService;
    private final AdventureReviewService adventureReviewService;
    private final InstructorSubscriptionService instructorSubscriptionService;
    private final AdventureReservationService adventureReservationService;
    private final InstructorComplaintService instructorComplaintService;

    public AdventureController(AdventureService adventureService, UserService userService, AdventureReviewService adventureReviewService, InstructorSubscriptionService instructorSubscriptionService, AdventureReservationService adventureReservationService, InstructorComplaintService instructorComplaintService) {
        this.adventureService = adventureService;
        this.userService = userService;
        this.adventureReviewService = adventureReviewService;
        this.instructorSubscriptionService = instructorSubscriptionService;
        this.adventureReservationService = adventureReservationService;
        this.instructorComplaintService = instructorComplaintService;
    }

    @RequestMapping({"/adventures"})
    public String listAdventures(Model model){

        return listAdventuresByPage(model, 1, "name", "asc");
    }

    @GetMapping({"/adventures/page/{pageNumber}"})
    public String listAdventuresByPage(Model model,
                                           @PathVariable("pageNumber") int currentPage,
                                           @Param(value="sortField") String sortField,
                                           @Param(value="sortDirection") String sortDirection){

        System.out.println(sortField);
        if (sortField==null){sortField="id";}
        if(sortDirection==null){sortDirection="asc";}
        Page<Adventure> page = adventureService.findAll(currentPage, sortField, sortDirection);
        List<Adventure> entities = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("adventures", entities);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);

        return "adventures";
    }

    @RequestMapping(value = "/adventures/{id}", method = GET)
    public String showAdventure(Model model, @PathVariable("id") long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        Adventure adventure = adventureService.findById(id);
        Set<AdventureReview> adventureReviews = adventureReviewService.getAllByAdventureAndReviewStatus(adventure, ReviewStatus.ALLOWED);

        Boolean allowComplaint = adventureReservationService.existsByUserAndInstructor_Id(user, adventure.getInstructor().getId());

        Boolean subscribed = instructorSubscriptionService.existsByUserAndInstructor(user, adventure.getInstructor());

        model.addAttribute("allowComplaint", allowComplaint);
        model.addAttribute("adventureReviews", adventureReviews);
        model.addAttribute("adventure", adventureService.findById(id));
        model.addAttribute("subscribed", subscribed);

        return "adventure";
    }

    @PostMapping("/search/adventures")
    String listAdventureResluts(Model model, @ModelAttribute(value = "dateFrom") String dateFrom,
                                @ModelAttribute(value = "dateEnd") String dateEnd,
                                @ModelAttribute(value = "tag1") String tag1,
                                @ModelAttribute(value = "tag2") String tag2,
                                @ModelAttribute(value = "tag3") String tag3,
                                @ModelAttribute(value = "maxPrice") Float maxPrice,
                                @ModelAttribute(value = "minRating") Float minRating,
                                @ModelAttribute(value = "noOfPersons") Integer noOfPersons){


        return listAdventureResultsByPage(model,1, "id", "asc", dateFrom, dateEnd, tag1, tag2, tag3, maxPrice, minRating, noOfPersons );
    }

    @GetMapping("/search/adventures/page/{pageNumber}")
    String listAdventureResultsByPage(Model model, @PathVariable("pageNumber") int currentPage,
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


        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateEnd", dateEnd);
        System.out.println(dateFrom + "+++" + dateEnd);


        Page<Adventure> page = adventureService.findAdventureNotReserved(currentPage,
                sortField,
                sortDirection,
                LocalDateTime.parse(dateFrom+" 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse(dateEnd+" 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                maxPrice, minRating, noOfPersons, tag1, tag2, tag3);
        List<Adventure> listAdventures = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        model.addAttribute("search", true);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("adventures", listAdventures);
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



        return "adventures";
    }

    @PostMapping("/adventure/rate")
    String rateBoat(Model model, @Param(value = "adventureIdRate") Long adventureIdRate,
                    @Param(value = "content") String content, @Param(value="adventureratingValue") Float adventureratingValue,
                    Principal principal){

        AdventureReview review = new AdventureReview();
        User dbUser = userService.findByEmail(principal.getName());

        review.setAdventure(adventureService.findById(adventureIdRate));
        review.setUser(dbUser);
        review.setContent(content);
        review.setRating(adventureratingValue);
        review.setReviewTime(LocalDateTime.now());
        review.setReviewStatus(ReviewStatus.PENDING);
        adventureReviewService.save(1L, review);
        return "/userHomePage";
    }

    @PostMapping("/adventures/{adventureId}")
    String subscribe(Model model, @PathVariable @RequestParam("adventureId") Long adventureId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        Adventure adventure = adventureService.findById(adventureId);

        InstructorSubscription subscription = new InstructorSubscription();
        subscription.setInstructor(adventure.getInstructor());
        subscription.setUser(user);
        subscription.setDateOfSubscribing(LocalDate.now());
        instructorSubscriptionService.save(1L, subscription);

        return showAdventure(model, adventureId);
    }

    @PutMapping("/adventures/{adventureId}")
    String unsubscribe(Model model, @PathVariable @RequestParam("adventureId") Long adventureId){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        Adventure adventure = adventureService.findById(adventureId);

        instructorSubscriptionService.deleteById(
                instructorSubscriptionService.findInstructorSubscriptionByInstructorAndUser
                        (adventure.getInstructor(), user).getId());

        return showAdventure(model, adventureId);
    }

    @PostMapping("/adventures/{adventureId}/complaint")
    String makeComplaint(Model model, @PathVariable @RequestParam("adventureId") Long adventureId, @RequestParam("complaintContent") String complaintContent){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);

        InstructorComplaint complaint = new InstructorComplaint();
        complaint.setInstructor(adventureService.findById(adventureId).getInstructor());
        complaint.setUser(user);
        complaint.setComplaintDate(LocalDate.now());
        complaint.setContent(complaintContent);
        instructorComplaintService.save(1L, complaint);

        return "index";
    }

}
