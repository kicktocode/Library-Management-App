package com.example.library_app.security;

import com.example.library_app.service.TokenService;
import com.example.library_app.service.UserDetailServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class JwtFilter extends OncePerRequestFilter {

    private final TokenService jwtTokenService;
    private final UserDetailServiceImpl jwtUserDetailService;

    public JwtFilter(TokenService jwtTokenService, UserDetailServiceImpl jwtUserDetailService) {
        this.jwtTokenService = jwtTokenService;
        this.jwtUserDetailService = jwtUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

           String token = getToken(request);
           String username;
           try {
               if (!token.isBlank()) {
                   username = jwtTokenService.verifyJWT(token).getSubject();
                   UserDetails userDetails = jwtUserDetailService.loadUserByUsername(username);
                   UsernamePasswordAuthenticationToken authenticationToken =
                           new UsernamePasswordAuthenticationToken(userDetails, null,
                                   userDetails.getAuthorities());
                   authenticationToken.setDetails(new WebAuthenticationDetailsSource()
                           .buildDetails(request));
                   SecurityContextHolder.getContext().setAuthentication(authenticationToken);
               }

               filterChain.doFilter(request, response);
           }catch (Exception e)
           {
               sendError(response,e);
           }

    }

    private void sendError(HttpServletResponse response, Exception e) throws IOException{
        response.setContentType("application/json");
        Map<String,String> errors= new HashMap<>();
        errors.put("error",e.getMessage());
        ObjectMapper mapper= new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(errors));


    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header==null || !header.startsWith("Bearer "))
        {
            return " ";
        }
        return  header.substring(7);
    }
}
