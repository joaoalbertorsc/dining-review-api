package com.codecademy.dining_review_api.controller;

import com.codecademy.dining_review_api.model.User;
import com.codecademy.dining_review_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping
    User createUser(
            @RequestBody User user
    ){
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isEmpty()){
            return null;
        } else {
            User newUser = optionalUser.get();
            return userRepository.save(newUser);
        }
    }
    @GetMapping
    List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/{userName}")
    User getUserByName(
            @PathVariable String userName
    ){
        Optional<User> optionalUser = userRepository.findByDisplayName(userName);
        return optionalUser.orElse(null);
    }
    @PutMapping("/{userName}")
    User updateUserByName(
            @PathVariable String userName,
            @RequestBody User updatedUser
    ){
        Optional<User> optionalUser = userRepository.findByDisplayName(userName);
        if (optionalUser.isPresent()){
            User existingUser = optionalUser.get();

            existingUser.setCity(updatedUser.getCity());
            existingUser.setDisplayName(updatedUser.getDisplayName());
            existingUser.setState(updatedUser.getState());
            existingUser.setZipcode(updatedUser.getZipcode());
            existingUser.setInterestedInDairyAllergies(updatedUser.isInterestedInDairyAllergies());
            existingUser.setInterestedInEggAllergies(updatedUser.isInterestedInEggAllergies());
            existingUser.setInterestedInPeanutAllergies(updatedUser.isInterestedInPeanutAllergies());

            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }
}
