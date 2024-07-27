package com.example.library_app.Controller;

import com.example.library_app.dto.TokenResponseDTO;
import com.example.library_app.dto.request.LoginRequest;

import com.example.library_app.dto.request.SignUpRequest;
import com.example.library_app.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

//    @PostMapping("/login")
//    public ResponseEntity<TokenResponseDTO> authentication (@RequestBody
//                                                            LoginRequest loginRequest)
//    {
//        return ResponseEntity.ok(authService.login(loginRequest));
//    }
}
