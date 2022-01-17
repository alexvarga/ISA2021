package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import rs.ac.uns.ftn.isaprojekat.model.*;
import rs.ac.uns.ftn.isaprojekat.service.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Set;

@Controller
public class ReviewsController {

    private final UserService userService;

    private final BoatReviewService boatReviewService;
    private final BoatService boatService;

    private final VacationHouseReviewService vacationHouseReviewService;
    private final VacationHouseService vacationHouseService;

    private final AdventureReviewService adventureReviewService;
    private final AdventureService adventureService;

    public ReviewsController(UserService userService, BoatReviewService boatReviewService, BoatService boatService, VacationHouseReviewService vacationHouseReviewService, VacationHouseService vacationHouseService, AdventureReviewService adventureReviewService, AdventureService adventureService) {
        this.userService = userService;
        this.boatReviewService = boatReviewService;
        this.boatService = boatService;
        this.vacationHouseReviewService = vacationHouseReviewService;
        this.vacationHouseService = vacationHouseService;
        this.adventureReviewService = adventureReviewService;
        this.adventureService = adventureService;
    }

    @GetMapping("/admin/reviews")
    String reviewsHub(){

        return "admin_reviews_hub";
    }

    @GetMapping("/admin/reviews/boats")
    String showBoatReviews(Model model){
        Set<BoatReview> reviews = boatReviewService.getAllByReviewStatus(ReviewStatus.PENDING);

        model.addAttribute("reviews", reviews);


        return "reviews/boats_reviews";
    }

    @PostMapping("/admin/reviews/boats")
    String allowBoatReview(Model model, @Param(value = "reviewId") Long reviewId) throws UnsupportedEncodingException, MessagingException {
        // (overall rating * total ratings + new rating )/(total ratings+1)

        BoatReview review = boatReviewService.findById(reviewId);
        Float rating = review.getRating();
        Boat boat = boatService.findById(review.getBoat().getId());
        Integer numberOfRatings = boat.getNoOfRatings();
        Float overallRating = boat.getAvgRating();
        Float newRating = (overallRating*numberOfRatings+rating)/(numberOfRatings+1);
        boat.setAvgRating(newRating);
        boat.setNoOfRatings(numberOfRatings+1);
        review.setReviewStatus(ReviewStatus.ALLOWED);

        String ownerMail = boat.getOwner().getEmail();
        String userFirst = review.getUser().getFirstName();
        String userLast = review.getUser().getLastName();
        String reviewContent = review.getContent();
        Float reviewRating = review.getRating();

        userService.sendReviewInfoEmail(boat.getName(),
                " brod", reviewContent, reviewRating, userFirst, userLast, ownerMail);


        boatService.save(1L, boat);
        boatReviewService.save(1L, review);

        return showBoatReviews(model);
    }

    @PutMapping("/admin/reviews/boats")
    String disallowBoatReview(Model model, @Param(value = "reviewId") Long reviewId){

        BoatReview review = boatReviewService.findById(reviewId);
        review.setReviewStatus(ReviewStatus.DECLINED);

        boatReviewService.save(1L, review);


        return showBoatReviews(model);
    }


    @GetMapping("/admin/reviews/houses")
    String showHouseReviews(Model model){
        Set<VacationHouseReview> reviews = vacationHouseReviewService.getAllByReviewStatus(ReviewStatus.PENDING);

        model.addAttribute("reviews", reviews);


        return "reviews/houses_reviews";
    }


    @PostMapping("/admin/reviews/houses")
    String allowHouseReview(Model model, @Param(value = "reviewId") Long reviewId) throws UnsupportedEncodingException, MessagingException {
        // (overall rating * total ratings + new rating )/(total ratings+1)

        VacationHouseReview review = vacationHouseReviewService.findById(reviewId);
        Float rating = review.getRating();
        VacationHouse house = vacationHouseService.findById(review.getVacationHouse().getId());
        Integer numberOfRatings = house.getNoOfRatings();
        Float overallRating = house.getAvgRating();
        Float newRating = (overallRating*numberOfRatings+rating)/(numberOfRatings+1);
        house.setAvgRating(newRating);
        house.setNoOfRatings(numberOfRatings+1);
        review.setReviewStatus(ReviewStatus.ALLOWED);


        String ownerMail = house.getVacationHouseOwner().getEmail();
        System.out.println("owner mail "+ownerMail);
        String userFirst = review.getUser().getFirstName();
        String userLast = review.getUser().getLastName();
        String reviewContent = review.getContent();
        Float reviewRating = review.getRating();

        userService.sendReviewInfoEmail(house.getName(),
                "a vikendica", reviewContent, reviewRating, userFirst, userLast, ownerMail);

        vacationHouseService.save(1L, house);
        vacationHouseReviewService.save(1L, review);

        return showHouseReviews(model);
    }

    @PutMapping("/admin/reviews/houses")
    String disallowHouseReview(Model model, @Param(value = "reviewId") Long reviewId){

        VacationHouseReview review = vacationHouseReviewService.findById(reviewId);
        review.setReviewStatus(ReviewStatus.DECLINED);

        vacationHouseReviewService.save(1L, review);


        return showHouseReviews(model);
    }


    @GetMapping("/admin/reviews/adventures")
    String showAdventureReviews(Model model){
        Set<AdventureReview> reviews = adventureReviewService.getAllByReviewStatus(ReviewStatus.PENDING);

        model.addAttribute("reviews", reviews);


        return "reviews/adventures_reviews";
    }

    @PostMapping("/admin/reviews/adventures")
    String allowAdventureReview(Model model, @Param(value = "reviewId") Long reviewId)
            throws UnsupportedEncodingException, MessagingException {
        // (overall rating * total ratings + new rating )/(total ratings+1)

        AdventureReview review = adventureReviewService.findById(reviewId);
        Float rating = review.getRating();
        Adventure adventure = adventureService.findById(review.getAdventure().getId());
        Integer numberOfRatings = adventure.getNoOfRatings();
        Float overallRating = adventure.getAvgRating();
        Float newRating = (overallRating*numberOfRatings+rating)/(numberOfRatings+1);
        adventure.setAvgRating(newRating);
        adventure.setNoOfRatings(numberOfRatings+1);
        review.setReviewStatus(ReviewStatus.ALLOWED);

        String ownerMail = adventure.getInstructor().getEmail();
        String userFirst = review.getUser().getFirstName();
        String userLast = review.getUser().getLastName();
        String reviewContent = review.getContent();
        Float reviewRating = review.getRating();

        userService.sendReviewInfoEmail(adventure.getName(),
                "a avantura", reviewContent, reviewRating, userFirst, userLast, ownerMail);

        adventureService.save(1L, adventure);
        adventureReviewService.save(1L, review);





        return showAdventureReviews(model);
    }

    @PutMapping("/admin/reviews/adventures")
    String disallowAdventureReview(Model model, @Param(value = "reviewId") Long reviewId){

        AdventureReview review = adventureReviewService.findById(reviewId);
        review.setReviewStatus(ReviewStatus.DECLINED);

        adventureReviewService.save(1L, review);


        return showAdventureReviews(model);
    }
}
