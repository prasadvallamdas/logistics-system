package com.alpha.logisticsproject.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.alpha.logisticsproject.entity.User;
import com.alpha.logisticsproject.repository.UserRepository;
import com.alpha.logisticsproject.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> loginAndIssueToken(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("username");
        String password = loginRequest.get("password");
        String selectedRole = loginRequest.get("role");

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            
            Optional<User> systemUser = userRepository.findAll().stream()
                    .filter(u -> u.getMail() != null && u.getMail().equalsIgnoreCase(email))
                    .findFirst();

            if (systemUser.isEmpty() || !systemUser.get().getRole().equalsIgnoreCase(selectedRole)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Authorization Exception: Role selection tier mismatch."));
            }

            String token = jwtUtil.generateToken(email, selectedRole.toUpperCase());
            
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("username", systemUser.get().getName());
            response.put("role", selectedRole.toUpperCase());
            
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Authentication Failure: Invalid username or password keys."));
        }
    }
}