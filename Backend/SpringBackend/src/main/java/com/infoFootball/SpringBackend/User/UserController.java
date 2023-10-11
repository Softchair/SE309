package com.infoFootball.SpringBackend.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is for controlling a user entity
 * It has all the functions to edit and delete as well as return all users
 * @Author Camden Fergen and Bryan Pope
 */
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    final private String success = "{\"message\":\"success\"}";
    final private String failure = "{\"message\":\"failure\"}";

    /**
     * Gets all users
     * @return list of all users
     */
    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * Gets a user by username
     * @param username username of user
     * @return user related to username
     */
    @GetMapping(path = "/users/{username}")
    User getUserByUsername(@PathVariable String username){
        return userRepository.findByUsername(username);
    }

    /**
     * Updates user
     * @param username username of user
     * @param request the updated user info
     * @return new user
     */
    @PutMapping("/users/{username}")
    User updateUser(@PathVariable String username, @RequestBody User request){
        User user = userRepository.findByUsername(username);
        if(user == null) {
            return null;
        }
        userRepository.save(request);
        return userRepository.findByUsername(username);
    }

    /**
     * Deletes a user by username
     * @param username username of user to be deleted
     * @return if user was successfully deleted
     */
    @DeleteMapping("/users/{username}")
    String deleteUser(@PathVariable String username){
        userRepository.deleteByUsername(username);
        return success;
    }
}