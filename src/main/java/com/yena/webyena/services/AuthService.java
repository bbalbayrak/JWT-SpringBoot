package com.yena.webyena.services;

import com.yena.webyena.auth.AuthenticationResponse;
import com.yena.webyena.auth.LoginRequest;
import com.yena.webyena.auth.RegisterRequest;
import com.yena.webyena.entities.Role;
import com.yena.webyena.entities.Users;
import com.yena.webyena.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


     public AuthenticationResponse createUser(RegisterRequest request) {
        var user = Users.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .company(request.getCompany())
                .relatedCompany(request.getRelatedCompany())
                .role(Role.INTERNAL)
                .build();
         userRepository.save(user);
         var jwtToken = jwtService.generateToken2(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken2(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }
}
