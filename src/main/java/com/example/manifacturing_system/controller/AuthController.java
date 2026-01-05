package com.example.manifacturing_system.controller;

import com.example.manifacturing_system.model.User;
import com.example.manifacturing_system.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserRepository repo;

    public AuthController(UserRepository repo) {
        this.repo = repo;
    }

    // ✅ SIGNUP
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        if (repo.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        repo.save(user); // password stored AS-IS
        return "Signup successful";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> req) {

        User user = repo.findByEmail(req.get("email"))
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!user.getPassword().equals(req.get("password"))) {
            throw new RuntimeException("Invalid password");
        }

        return Map.of(
                "message", "Login successful",
                "username", user.getUsername()
        );
    }

}
