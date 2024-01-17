package com.example.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.dto.LoginDTO;
import com.example.pos.entity.UserEntity;
import com.example.pos.repository.UserRepository;
import com.example.pos.security.jwt.JwtUtils;
import com.example.pos.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;
    
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())  
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody UserEntity userEntity){
        if(userRepository.existsByUsername(userEntity.getUsername())){
            return ResponseEntity.badRequest().body("UserName already in use");
        }

        if(userRepository.existsByEmail(userEntity.getEmail())){
            return ResponseEntity.badRequest().body("Email already in user");
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(userEntity.getUsername());
        newUser.setEmail(userEntity.getEmail());
        newUser.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return ResponseEntity.ok(userService.createUser(newUser));
    }

    
    
}
