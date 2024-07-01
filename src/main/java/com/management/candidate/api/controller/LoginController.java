package com.management.candidate.api.controller;

import com.management.candidate.application.service.UserService;
import com.management.candidate.domain.LoginRequest;
import com.management.candidate.domain.LoginResponse;
import com.management.candidate.domain.RegisterRequest;
import com.management.candidate.domain.User;
import com.management.candidate.infrastructure.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/authentication")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/signin")
    @Operation(summary = "Metodo signin", description = "Devuelve el token en jwt")
    public LoginResponse authenticateUser(@RequestBody LoginRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken((UserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    @PostMapping("/signup")
    @Operation(summary = "Metodo signup", description = "Registra un Usuario en la base de datos")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        Optional<User> existingUserByUsername = userService.findByUsername(registerRequest.getUsername());
        if (existingUserByUsername.isPresent()) {
            return ResponseEntity.status(409).body("Usuario ya existe"); // Conflict if username already exists
        }
        userService.saveUser(registerRequest);
        return ResponseEntity.ok("Usuario Registrado Correctamente");
    }

}
