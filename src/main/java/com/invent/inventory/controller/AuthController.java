package com.invent.inventory.controller;

import com.invent.inventory.dto.ErrorResponseDto;
import com.invent.inventory.entity.User;
import com.invent.inventory.payload.LoginRequest;
import com.invent.inventory.payload.RegisterRequest;
import com.invent.inventory.repository.UserRepository;
import com.invent.inventory.security.JwtUtil;
import com.invent.inventory.security.UserDetailsImpl;
import com.invent.inventory.service.UserDetailsServiceImpl;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

        private final AuthenticationManager authManager;
        private final UserRepository userRepository;
        private final JwtUtil jwtUtil;
        private final PasswordEncoder passwordEncoder;

        public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, UserRepository userRepository,
                        PasswordEncoder passwordEncoder) {
                this.userRepository = userRepository;
                this.authManager = authManager;
                this.jwtUtil = jwtUtil;
                this.passwordEncoder = passwordEncoder;
        }

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginRequest req) {
                try {
                        Authentication auth = authManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
                        String token = jwtUtil.generateToken(req.getUsername());
                        User user = userRepository.findByUsername(req.getUsername()).orElse(null);
                        if (user == null) {
                                return ResponseEntity.badRequest().body(
                                                new ErrorResponseDto("User not found",
                                                                "No user found with the provided username"));
                        }

                        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
                        User u = userDetails.getUser();
                        // Update last login time
                        u.setLastLogin(java.time.LocalDateTime.now());
                        userRepository.save(u);

                        return ResponseEntity.ok(
                                        java.util.Map.of(
                                                        "username", user.getUsername(),
                                                        "token", token));
                } catch (Exception e) {
                        return ResponseEntity
                                        .badRequest()
                                        .body(new ErrorResponseDto("Error during login", e.getMessage()));
                }
        }

        @PostMapping("/register")
        public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
                if (userRepository.existsByUsername(req.getUsername())) {
                        return ResponseEntity
                                        .badRequest()
                                        .body(new ErrorResponseDto("Username already exists"));
                }

                User user = User.builder()
                                .username(req.getUsername())
                                .password(passwordEncoder.encode(req.getPassword()))
                                .role(req.getRole())
                                .build();

                userRepository.save(user);

                return ResponseEntity.ok(
                                java.util.Map.of(
                                                "message", "User registered successfully",
                                                "username", user.getUsername()));

        }

}