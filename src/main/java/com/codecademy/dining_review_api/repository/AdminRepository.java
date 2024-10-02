package com.codecademy.dining_review_api.repository;

import com.codecademy.dining_review_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<User, Long> {
}
