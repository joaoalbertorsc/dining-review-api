package com.codecademy.dining_review_api.repository;

import com.codecademy.dining_review_api.model.Restaurant;
import com.codecademy.dining_review_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByName(String restaurantName);
    Optional<Restaurant> findByAddress(String address);
    Optional<Restaurant> findByCuisineType(String cuisineType);
}
