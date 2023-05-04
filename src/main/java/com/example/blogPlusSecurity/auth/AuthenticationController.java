package com.example.blogPlusSecurity.auth;

import com.example.blogPlusSecurity.entity.User;
import com.example.blogPlusSecurity.request.AuthenticationRequest;
import com.example.blogPlusSecurity.request.RegisterRequest;
import com.example.blogPlusSecurity.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<User> register (@RequestBody RegisterRequest userDTO) {
//    public ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterRequest userDTO) {
        return new ResponseEntity<>(service.register(userDTO), HttpStatus.OK);
//                return ResponseEntity.ok(service.register(userDTO));

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
