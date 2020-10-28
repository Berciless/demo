package com.passwordStorage.demo.controller;


import com.passwordStorage.demo.dto.AuthRequest;
import com.passwordStorage.demo.dto.AuthResponse;
import com.passwordStorage.demo.dto.UserDto;
import com.passwordStorage.demo.repository.UserRepository;
import com.passwordStorage.demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/auth/hashAndPepperSignUp")
    public ResponseEntity<String> hashAndPepperSignUp(@RequestBody AuthRequest authRequest){
        if(userRepository.findById(authRequest.getUsername()).isPresent()){
            return new ResponseEntity<>("User Allready Exists", HttpStatus.BAD_REQUEST);
        }
        authService.hashAndPepperSignUp(authRequest);
        return new ResponseEntity<>("Simple User Created !", HttpStatus.OK);
    }

    @PostMapping("/auth/hashAndPepperLogIn")
    public AuthResponse hashAndPepperLogIn(@RequestBody AuthRequest authRequest){
        return authService.hashAndPepperLogIn(authRequest);
    }



    @PostMapping("/auth/hashSignUp")
    public ResponseEntity<String> hashSignUp(@RequestBody AuthRequest authRequest){
        if(userRepository.findById(authRequest.getUsername()).isPresent()){
            return new ResponseEntity<>("User Allready Exists", HttpStatus.BAD_REQUEST);
        }
        authService.hashSignUp(authRequest);
        return new ResponseEntity<>("Hash User Created !", HttpStatus.OK);
    }

    @PostMapping("/auth/hashLogIn")
    public AuthResponse hashLogIn(@RequestBody AuthRequest authRequest){
        return authService.hashLogIn(authRequest);
    }



    @GetMapping("/auth/data")
    public ResponseEntity<List<UserDto>> readData(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(authService.getAllUsers());
    }
}
