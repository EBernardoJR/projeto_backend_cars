package com.example.restservice.controller;


import com.example.restservice.dtos.CreateUserDTO;
import com.example.restservice.model.User;
import com.example.restservice.repository.UserRepository;
import com.example.restservice.dtos.AuthenticationDTO;
import com.example.restservice.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto){
        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        Authentication authenticate = authenticationManager.authenticate(credentials);

        String token = authService.generateToken((User) authenticate.getPrincipal());

        return ResponseEntity.ok(token);
    }

    @GetMapping("/list")
    public ResponseEntity list(){
        List<User> users = userRepository.findAll();

        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CreateUserDTO registerDTO){

        if(userRepository.findByLogin(registerDTO.login()) != null){
            return ResponseEntity.badRequest().build();
        }

        User user =  new User();
        user.setLogin(registerDTO.login());
        user.setPassword(new BCryptPasswordEncoder().encode(registerDTO.password()));
        user.setRole(registerDTO.role());

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

}
