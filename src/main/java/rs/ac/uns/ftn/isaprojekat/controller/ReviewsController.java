package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import rs.ac.uns.ftn.isaprojekat.model.BoatReview;
import rs.ac.uns.ftn.isaprojekat.model.ReviewStatus;
import rs.ac.uns.ftn.isaprojekat.service.BoatReviewService;

import java.util.Set;

@Controller
public class ReviewsController {

    private final BoatReviewService boatReviewService;

    public ReviewsController(BoatReviewService boatReviewService) {
        this.boatReviewService = boatReviewService;
    }

    @GetMapping("/admin/reviews")
    String showReviews(Model model){
        Set<BoatReview> reviews = boatReviewService.getAllByReviewStatus(ReviewStatus.PENDING);

        for(BoatReview review:reviews){
            System.out.println(review.getContent());
        }

        model.addAttribute("reviews", reviews);


        return "reviews/boats_reviews";
    }

    @PostMapping("/admin/reviews")
    String allowReview(Model model, @Param(value = "reviewId") Long reviewId){

        BoatReview review = boatReviewService.findById(reviewId);
        review.setReviewStatus(ReviewStatus.ALLOWED);
        boatReviewService.save(1L, review);

        return showReviews(model);
    }

    @PutMapping("/admin/reviews")
    String disallowReview(Model model, @Param(value = "reviewId") Long reviewId){

        BoatReview review = boatReviewService.findById(reviewId);
        review.setReviewStatus(ReviewStatus.DECLINED);

        boatReviewService.save(1L, review);

        System.out.println(reviewId+ "iz disallow review");

        return showReviews(model);
    }

}
