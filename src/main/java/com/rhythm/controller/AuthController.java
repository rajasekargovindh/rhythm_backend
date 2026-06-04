package com.rhythm.controller;


import com.rhythm.dto.LoginRequestDto;
import com.rhythm.dto.RegisterRequestDto;
import com.rhythm.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

    @RestController
    @RequestMapping("/api/auth")
    public class AuthController {

        @Autowired
        private AuthService authService;

        @PostMapping("/register")
        public String register(
                @Valid
                @RequestBody
                RegisterRequestDto request
        ) {

            return authService.register(
                    request
            );
        }

        @PostMapping("/login")
        public Map<String, String> login(
                @Valid
                @RequestBody
                LoginRequestDto request
        ) {

            return authService.login(
                    request
            );
        }

        @PostMapping("/logout")
        public String logout()  {

            return authService.logout();
        }
    }


