package com.example.library_app.service;

import com.example.library_app.dto.UserDto;
import com.example.library_app.model.User;
import com.example.library_app.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.example.library_app.exceptions.GenericException;




import java.util.Optional;
import java.util.function.Supplier;

@Service
@Log4j2
public class UserService {
     private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User create(User user)
    {
        return userRepository.save(user);
    }

    public User findUserByUsername(String username)
    {
        return userRepository.findByUsername(username).orElseThrow(
                notFoundUser(HttpStatus.NOT_FOUND)
        );
    }

    public UserDto findUser(String username)
    {
        var user= findUserByUsername(username);
        return UserDto.builder()
                .userName(user.getUserName())
                .id(user.getId())
                .role(user.getRole())
                .build();
    }

    public UserDto findUserInContext()
    {
       final Authentication authentication= Optional.ofNullable(SecurityContextHolder
               .getContext().getAuthentication()).orElseThrow(notFoundUser(HttpStatus.UNAUTHORIZED));
       final UserDetails details= Optional.ofNullable((UserDetails) authentication
               .getPrincipal()).orElseThrow(notFoundUser(HttpStatus.UNAUTHORIZED));
       return  findUser(details.getUsername());
    }

    private static Supplier<GenericException> notFoundUser(HttpStatus httpStatus)
    {
        return ()->GenericException.builder().httpStatus(httpStatus)
                .errorMessage("User not found").build();
    }

    public boolean existsByUsername(String username)
    {
        return userRepository.existsByUsername(username);
    }
}
