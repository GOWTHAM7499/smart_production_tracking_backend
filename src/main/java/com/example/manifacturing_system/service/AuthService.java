package com.example.manifacturing_system.service;

import com.example.manifacturing_system.model.User;
import com.example.manifacturing_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // SIGNUP
    public String signup(String email, String username, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            return "EMAIL_EXISTS";
        }

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password); // ✅ store EXACT password

        userRepository.save(user);
        return "SUCCESS";
    }

    // LOGIN
    public boolean login(String email, String password) {

        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) return false;

        User user = userOpt.get();

        // ✅ plain text comparison
        return user.getPassword().equals(password);
    }
}
