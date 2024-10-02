package com.codecademy.dining_review_api.controller;

import com.codecademy.dining_review_api.enums.ReviewStatus;
import com.codecademy.dining_review_api.model.AdminReviewAction;
import com.codecademy.dining_review_api.model.DiningReview;
import com.codecademy.dining_review_api.repository.AdminRepository;
import com.codecademy.dining_review_api.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/reviews/peding")
    List<DiningReview> getPedingsReviews(){
        return reviewRepository.findByStatus(ReviewStatus.PENDING);
    }
    @PostMapping("/reviews/{reviewId}/action")
    List<DiningReview> createAction(
            @PathVariable Integer reviewId,
            @RequestBody AdminReviewAction action
    ){
        Optional<DiningReview> reviewOptional = reviewRepository.findById(Long.valueOf(reviewId));
        if (reviewOptional.isPresent()){
            DiningReview review = reviewOptional.get();
            if(action.isAccepted()){
                review.setStatus(ReviewStatus.ACCEPTED);
            } else {
                review.setStatus(ReviewStatus.REJECTED);
            }
            reviewRepository.save(review);
            return reviewRepository.findByStatus(ReviewStatus.ACCEPTED);
        } else {
            return null;
        }
    }
}
