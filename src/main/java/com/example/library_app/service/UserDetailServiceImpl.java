package com.example.library_app.service;

import com.example.library_app.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Log4j2
public class UserDetailServiceImpl implements UserDetailsService {
    private  final UserService userService;

    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        var roles = Stream.of(user.getRole())
                .map(x->new SimpleGrantedAuthority(x.name()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUserName()
        ,user.getPassword(),roles);
    }
}
