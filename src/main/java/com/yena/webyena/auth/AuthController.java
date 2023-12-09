package com.yena.webyena.auth;


import com.yena.webyena.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.createUser(request));
    }

    @PostMapping(value = "/login")
    @CrossOrigin
    public ResponseEntity<AuthenticationResponse> createUser(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
}
