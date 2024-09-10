package com.techlabs.bank.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.bank.dto.JwtAuthResponse;
import com.techlabs.bank.dto.LoginDto;
import com.techlabs.bank.dto.RegistrationDto;

import com.techlabs.bank.entity.User;
import com.techlabs.bank.service.AuthService;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> register(@RequestBody RegistrationDto registerDto, @RequestParam String captchaResponse) {
        return ResponseEntity.ok(authService.register(registerDto, captchaResponse));
    }
    
    

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto, @RequestParam String captchaResponse) {
        String token = authService.login(loginDto, captchaResponse);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

}