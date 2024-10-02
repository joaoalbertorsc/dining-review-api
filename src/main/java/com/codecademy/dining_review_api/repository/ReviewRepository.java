package com.codecademy.dining_review_api.repository;

import com.codecademy.dining_review_api.enums.ReviewStatus;
import com.codecademy.dining_review_api.model.DiningReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<DiningReview, Long> {


    List<DiningReview> findByStatus(ReviewStatus reviewStatus);

}
