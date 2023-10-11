package com.infoFootball.SpringBackend.Register;

import com.infoFootball.SpringBackend.User.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Springboot controller for registering a user
 * @Author Camden Fergen
 */
@RestController
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login/register")
    public String newUser(@RequestBody User newUser) {
        userRepository.save(newUser);
        return "Success";
    }
}