package com.example.library_app.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestEndPoint {
    @GetMapping("/admin")
    public String adminEndPoint() {
        return "admin";
    }

    @GetMapping("/user")
    public String userEndPoint()
    {
        return "user";
    }
    @GetMapping("/public")
    public String publicEndPoint()
    {
        return "public";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/justMapping")
    public String justAdmin()
    {
        return "justAdmin";
    }

    @GetMapping("/username")
    public String getMySelf()
    {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getUsername().toString();
    }
}
