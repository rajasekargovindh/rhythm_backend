package com.rhythm.service;


import com.rhythm.dto.LoginRequestDto;
import com.rhythm.dto.RegisterRequestDto;
import com.rhythm.entity.User;
import com.rhythm.repository.UserRepository;
import com.rhythm.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

    @Service
    public class AuthService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private JwtUtil jwtUtil;

        public String register(
                RegisterRequestDto request
        ) {

            if (
                    userRepository.existsByEmail(
                            request.getEmail()
                    )
            ) {

                throw new RuntimeException(
                        "Email already exists"
                );
            }

            User user = new User();

            user.setName(
                    request.getName()
            );

            user.setEmail(
                    request.getEmail()
            );

            user.setPassword(
                    passwordEncoder.encode(
                            request.getPassword()
                    )
            );

            userRepository.save(user);

            return "User Registered Successfully";
        }

        public Map<String, String> login(
                LoginRequestDto request
        ) {

            User user = userRepository
                    .findByEmail(
                            request.getEmail()
                    )
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "User not found"
                            )
                    );

            boolean passwordMatch =
                    passwordEncoder.matches(
                            request.getPassword(),
                            user.getPassword()
                    );

            if (!passwordMatch) {

                throw new RuntimeException(
                        "Invalid Password"
                );
            }

            String token =
                    jwtUtil.generateToken(
                            user.getEmail()
                    );

            Map<String, String> response =
                    new HashMap<>();

            response.put(
                    "token",
                    token
            );

            return response;
        }

        public String logout() {

            return "Logout Successful";
        }
    }

