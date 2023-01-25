package com.java.newscycle.controller;

import com.java.newscycle.dto.Auth.AuthResponseDTO;
import com.java.newscycle.dto.Auth.LoginDto;
import com.java.newscycle.dto.Auth.RegisterDto;
import com.java.newscycle.entity.Role;
import com.java.newscycle.entity.Users;
import com.java.newscycle.repository.RoleRepository;
import com.java.newscycle.repository.UsersRepository;
import com.java.newscycle.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class UserController {

    private AuthenticationManager authenticationManager;
    private UsersRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, UsersRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/user/signin")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        Users user = userRepository.findUsersByUsername(loginDto.getUsername());
        return new ResponseEntity<>(new AuthResponseDTO(token, user.getUsername(), user.getId()), HttpStatus.OK);
    }

    @PostMapping("/user/signup")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.findUsersByUsername(registerDto.getUsername()) != null) {
            return new ResponseEntity<>("Username is not available", HttpStatus.BAD_REQUEST);
        }

        Users user = new Users();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        user.setCreationDate(sqlDate);

        Role roles = roleRepository.findByName("USER");
        user.setRoles(Collections.singletonList(roles));


        userRepository.save(user);

        return new ResponseEntity<>("User created", HttpStatus.OK);
    }
}