package com.alpha.logisticsproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.entity.User;
import com.alpha.logisticsproject.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // Direct REST target link for the registration fetch event in login.jsp
    @PostMapping("/saveuser")
    public ResponseEntity<ResponseStructure<User>> registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}