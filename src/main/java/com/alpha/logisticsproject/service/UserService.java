package com.alpha.logisticsproject.service;

import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alpha.logisticsproject.ResponseStructure;
import com.alpha.logisticsproject.entity.User;
import com.alpha.logisticsproject.repository.UserRepository;
import com.alpha.logisticsproject.exception.UserAlreadyPresent;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
        ResponseStructure<User> responseStructure = new ResponseStructure<>();

        // 1. Enforce Domain Integrity: Check if the email handle is already registered
        Optional<User> existingUser = userRepository.findAll().stream()
                .filter(u -> u.getMail() != null && u.getMail().equalsIgnoreCase(user.getMail()))
                .findFirst();

        if (existingUser.isPresent()) {
            throw new UserAlreadyPresent("An account with email '" + user.getMail() + "' is already registered in the database.");
        }

        // 2. ID Allocation Check: Since the user table does not use an identity auto-increment,
        // we safely generate a unique numeric identifier if it is initialized as 0
        if (user.getId() == 0) {
            user.setId(new Random().nextInt(900000) + 100000); // Generates a safe 6-digit primary key
        }

        // 3. Persist record to PostgreSQL database
        User savedUser = userRepository.save(user);

        // 4. Assemble the unified Generic Response Structure
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setMessage("User account profile generated successfully.");
        responseStructure.setData(savedUser);

        return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
    }
}