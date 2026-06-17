package com.alpha.logisticsproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alpha.logisticsproject.entity.User;
import com.alpha.logisticsproject.repository.UserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> systemUser = userRepository.findAll().stream()
                .filter(u -> u.getMail() != null && u.getMail().equalsIgnoreCase(email))
                .findFirst();

        if (systemUser.isEmpty()) {
            throw new UsernameNotFoundException("Identity not found in repository database: " + email);
        }

        User user = systemUser.get();
        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getMail());
        
        // Using {noop} text tag modifier to bypass password encoding check for direct testing comparisons
        builder.password("{noop}" + user.getPassword());
        builder.roles(user.getRole().toUpperCase());
        
        return builder.build();
    }
}