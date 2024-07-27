package com.example.library_app.service;

import com.example.library_app.dto.ErrorCode;
import com.example.library_app.dto.TokenResponseDTO;
import com.example.library_app.dto.UserDto;
import com.example.library_app.dto.request.LoginRequest;
import com.example.library_app.dto.request.SignUpRequest;
import com.example.library_app.exceptions.GenericException;
import com.example.library_app.model.Role;
import com.example.library_app.model.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public TokenResponseDTO login(LoginRequest loginRequest)
    {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
                            loginRequest.getPassword()));
            return  TokenResponseDTO.builder()
                    .accessToken(tokenService.generateToken(authentication))
                    .userDto(userService.findUser(loginRequest.getUserName()))
                    .build();
        }
        catch (Exception e)
        {
            throw GenericException.builder().errorCode(ErrorCode.UNAUTHORIZED)
                    .httpStatus(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Transactional
    public UserDto signup(SignUpRequest signUpRequest)
    {
        var isAlreadyRegistered = userService.existsByUsername(signUpRequest.getUsername());

        if(isAlreadyRegistered)
        {
            throw  GenericException.builder().httpStatus(HttpStatus.FOUND).errorMessage("User already exists").build();
        }

        var user= User.builder()
                .userName(signUpRequest.getUsername())
                .password(signUpRequest.getPassword())
                .role(Role.USER)
                .build();

        User fromDb= userService.create(user);
        return UserDto.builder()
                .id(fromDb.getId())
                .userName(fromDb.getUserName())
                .role(fromDb.getRole())
                .build();

    }
}
