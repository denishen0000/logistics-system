package com.logistics.system.auth.service;

import com.logistics.system.auth.dto.LoginRequest;
import com.logistics.system.auth.dto.RegisterRequest;
import com.logistics.system.entity.User;
import com.logistics.system.repository.UserRepository;
import com.logistics.system.auth.model.Role;
import com.logistics.system.config.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String register(RegisterRequest request) {
        // Check if email is already used
        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("Email is already in use");
        }

        // Create user entity
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // hash password
        user.setRole(Role.valueOf(request.getRole().toUpperCase())); // MAYBE GET FROM REQUEST???

        // Save to DB
        userRepository.save(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());

        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }


    public String login(LoginRequest request) {

        // Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // Generate JWT
        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }
}
