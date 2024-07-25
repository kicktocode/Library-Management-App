//package com.example.library_app.security;
//
//import com.example.library_app.service.TokenService;
//import com.example.library_app.service.UserDetailServiceImpl;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@Log4j2
//public class JwtFilter extends OncePerRequestFilter {
//
////    private final TokenService jwtTokenService;
////    private final UserDetailServiceImpl jwtUserDetailService;
////
////    public JwtFilter(TokenService jwtTokenService, UserDetailServiceImpl jwtUserDetailService) {
////        this.jwtTokenService = jwtTokenService;
////        this.jwtUserDetailService = jwtUserDetailService;
////    }
////
////    @Override
////    protected void doFilterInternal(HttpServletRequest request
////            , HttpServletResponse response, FilterChain filterChain)
////            throws ServletException, IOException {
////
////           String token = getToken(request);
////           String username;
////           try{
////               if(!token.isBlank())
////               {
////                   username = jwtTokenService.verifyJWT
////               }
////           }
////
////    }
////
////    private String getToken(HttpServletRequest request) {
////        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
////        if(header==null || !header.startsWith("Bearer "))
////        {
////            return " ";
////        }
////        return  header.substring(7);
////    }
//}
