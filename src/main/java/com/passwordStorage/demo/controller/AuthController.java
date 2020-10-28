package com.passwordStorage.demo.controller;


import com.passwordStorage.demo.dto.AuthRequest;
import com.passwordStorage.demo.dto.AuthResponse;
import com.passwordStorage.demo.repository.UserRepository;
import com.passwordStorage.demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/auth/signUp")
    public ResponseEntity<String> simpleSignUp(@RequestBody AuthRequest authRequest){
        if(userRepository.findById(authRequest.getUsername()).isPresent()){
            return new ResponseEntity<>("User Allready Exists", HttpStatus.BAD_REQUEST);
        }
        authService.simpleSignUp(authRequest);
        return new ResponseEntity<>("Simple User Created !", HttpStatus.OK);

    }
    @PostMapping("/auth/simpleLogIn")
        public AuthResponse simpleLogIn(@RequestBody AuthRequest authRequest){
        return authService.simpleLogIn(authRequest);
    }

    @GetMapping("/auth/data")
    public ResponseEntity<Stream<AuthRequest>> readData(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(authService.getAllUsers());
    }
}
