package com.codecademy.dining_review_api.controller;

import com.codecademy.dining_review_api.model.DiningReview;
import com.codecademy.dining_review_api.model.Restaurant;
import com.codecademy.dining_review_api.model.User;
import com.codecademy.dining_review_api.repository.RestaurantRepository;
import com.codecademy.dining_review_api.repository.ReviewRepository;
import com.codecademy.dining_review_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    Restaurant createRestaurant(
            @RequestBody Restaurant restaurantToCreate
    ) {
        Optional<Restaurant> optionalRestaurantN = restaurantRepository.findByName(restaurantToCreate.getName());
        Optional<Restaurant> optionalRestaurantZC = restaurantRepository.findByName(restaurantToCreate.getAddress());

        if (optionalRestaurantN.isPresent() && optionalRestaurantZC.isPresent()) {
            Restaurant restaurantN = optionalRestaurantN.get();
            Restaurant restaurantZC = optionalRestaurantZC.get();
            if (restaurantN.equals(restaurantZC)){
                return restaurantN;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    @GetMapping
    List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/{restaurantId}")
    Restaurant getRestaurantById(
            @PathVariable Integer restaurantId
    ) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(Long.valueOf(restaurantId));
        return restaurantOptional.orElse(null);
    }

    @PutMapping("/{restaurantId}")
    Restaurant updateRestaurant(
            @PathVariable Integer restaurantId,
            @RequestBody Restaurant restaurantUpdated
    ) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(Long.valueOf(restaurantId));
        if (restaurantOptional.isPresent()) {
            Restaurant existingRestaurant = restaurantOptional.get();

            existingRestaurant.setName(restaurantUpdated.getName());
            existingRestaurant.setAddress(restaurantUpdated.getAddress());
            existingRestaurant.setCuisineType(restaurantUpdated.getCuisineType());
            existingRestaurant.setPeanutScore(restaurantUpdated.getPeanutScore());
            existingRestaurant.setEggScore(restaurantUpdated.getEggScore());
            existingRestaurant.setDairyScore(restaurantUpdated.getDairyScore());
            existingRestaurant.setOverallScore(restaurantUpdated.getOverallScore());

            return existingRestaurant;
        } else {
            return null;
        }
    }
    @GetMapping("/search")
    List<Restaurant> searchRestaurants(
            @RequestParam("address") String address,
            @RequestParam("allergy") String allergy
    ){
        List<Restaurant> restaurantsOptional = restaurantRepository.findAll();
        List<Restaurant> restaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurantsOptional){
            if (allergy.contains("egg") && restaurant.getAddress().equals(address)){
                restaurants.add(restaurant);
            } else if (allergy.contains("peanut") && restaurant.getAddress().equals(address)) {
                restaurants.add(restaurant);
            } else if (allergy.contains("dairy") && restaurant.getAddress().equals(address)){
                restaurants.add(restaurant);
            } else {
                break;
            }
        }
        return restaurants;
    }

    @GetMapping("/search?zipcode={address}&allergy={allergy}")
    Restaurant getRestaurantByZipCodeAndAllergy(
            @PathVariable String address,
            @PathVariable String allergy
    ) {
        List<Restaurant> restaurantsOptional = restaurantRepository.findAll();
        for (Restaurant restaurant : restaurantsOptional){
            if (allergy.contains("egg") && restaurant.getAddress().equals(address)){
                return restaurant;
            } else if (allergy.contains("peanut") && restaurant.getAddress().equals(address)) {
                return restaurant;
            } else if (allergy.contains("dairy") && restaurant.getAddress().equals(address)){
                return restaurant;
            }
        }
        return null;
    }

    @PostMapping("/{restaurantId}/reviews")
    DiningReview createReview(
            @PathVariable Integer restaurantId,
            @RequestBody DiningReview review,
            @RequestBody User actualUser
    ) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(Long.valueOf(restaurantId));
        Optional<DiningReview> reviewOptional = reviewRepository.findById(review.getId());
        Optional<User> userOptional = userRepository.findById(actualUser.getId());

        if (
                restaurantOptional.isPresent()
                && reviewOptional.isPresent()
                && userOptional.isPresent()
        ) {
            Restaurant restaurant = restaurantOptional.get();
            DiningReview reviewCreated = reviewOptional.get();
            User user = userOptional.get();

            if (user.getDisplayName().equals(review.getSubmittedBy())) {
                reviewCreated.setRestaurantId(restaurant.getId());
                return reviewRepository.save(reviewCreated);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @GetMapping("/reviews")
    List<DiningReview> getAllReviews(){
        return reviewRepository.findAll();
    }

    @GetMapping("/{restaurantId}/reviews/{reviewId}")
    DiningReview getRestaurantReview(
            @PathVariable Integer restaurantId,
            @PathVariable Integer reviewId,
            @RequestBody User actualUser
    ) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(Long.valueOf(restaurantId));
        Optional<DiningReview> reviewOptional = reviewRepository.findById(Long.valueOf(reviewId));
        Optional<User> userOptional = userRepository.findById(actualUser.getId());

        if (restaurantOptional.isPresent() && reviewOptional.isPresent() && userOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            DiningReview reviewSelected = reviewOptional.get();
            User user = userOptional.get();

            if (reviewSelected.getRestaurantId().equals(restaurant.getId())
                    && reviewSelected.getSubmittedBy().equals(user.getDisplayName())) {
                return reviewSelected;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    @PutMapping("/{restaurantId}/reviews/{reviewId}")
    DiningReview updateRestaurantReview(
            @PathVariable Integer restaurantId,
            @PathVariable Integer reviewId,
            @RequestBody DiningReview reviewUpdated,
            @RequestBody User actualUser
    ) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(Long.valueOf(restaurantId));
        Optional<DiningReview> reviewOptional = reviewRepository.findById(Long.valueOf(reviewId));
        Optional<DiningReview> reviewUpdatedOptional = reviewRepository.findById(reviewUpdated.getId());
        Optional<User> userOptional = userRepository.findById(actualUser.getId());

        if (
                restaurantOptional.isPresent()
                && reviewOptional.isPresent()
                && reviewUpdatedOptional.isPresent()
                && userOptional.isPresent()
        ) {
            Restaurant restaurant = restaurantOptional.get();
            DiningReview existingReview = reviewOptional.get();
            DiningReview updatedReview = reviewOptional.get();
            User user = userOptional.get();

            if (existingReview.getRestaurantId().equals(restaurant.getId())
                    && existingReview.getSubmittedBy().equals(user.getDisplayName())) {
                existingReview.setSubmittedBy(updatedReview.getSubmittedBy());
                existingReview.setCommentary(updatedReview.getCommentary());
                existingReview.setPeanutScore(updatedReview.getPeanutScore());
                existingReview.setEggScore(updatedReview.getEggScore());
                existingReview.setStatus(updatedReview.getStatus());
                existingReview.setDairyScore(updatedReview.getDairyScore());
                existingReview.setPeanutScore(updatedReview.getPeanutScore());

                reviewRepository.save(existingReview);
                return existingReview;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }
}
