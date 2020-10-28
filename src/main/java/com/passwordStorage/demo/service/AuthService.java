package com.passwordStorage.demo.service;

import com.passwordStorage.demo.dto.AuthRequest;
import com.passwordStorage.demo.dto.AuthResponse;
import com.passwordStorage.demo.model.HashUser;
import com.passwordStorage.demo.model.User;
import com.passwordStorage.demo.repository.UserRepository;
import com.passwordStorage.demo.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;


@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Transactional
    public void simpleSignUp(AuthRequest authRequest){
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(authRequest.getPassword());
        userRepository.save(user);
    }

    @Transactional
    public void hashSignUp(AuthRequest authRequest) {
        User user = new User();
        String hashPassword = authRequest.getPassword()+"123";
        user.setUsername(authRequest.getUsername());
        user.setPassword(hashPassword);
        userRepository.save(user);
    }

    @Transactional
    public AuthResponse simpleLogIn(AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authRequest.getUsername());
        return new AuthResponse(token,authRequest.getUsername());
    }

    @Transactional(readOnly= true)
    public Stream<AuthRequest> getAllUsers(){
        return userRepository.findAll().stream().map(AuthRequest::new);
    }
}
