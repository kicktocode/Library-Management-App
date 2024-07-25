package com.example.library_app.service;


import com.example.library_app.exceptions.GenericException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;


@Service
@Log4j2
public class TokenService {
    @Value("${jwt-variables.KEY}")
    private String KEY;

    @Value("${jwt-variables.ISSUER}")
    private String ISSUER;

    @Value("${jwt-variables.EXPIRES_ACCESS_TOKEN_MINUTE}")
    private Integer EXPIRES_ACCESS_TOKEN_MINUTE;

    public String generateToken(Authentication auth)
    {
        String userName= ((UserDetails)auth.getPrincipal()).getUsername();
        return JWT.create()
                .withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis()+
                        (EXPIRES_ACCESS_TOKEN_MINUTE*60*1000)))
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(KEY.getBytes()));
    }

    public DecodedJWT verifyJWT(String token)
    {
        Algorithm algorithm = Algorithm.HMAC256(KEY.getBytes());
        JWTVerifier verifier= JWT.require(algorithm).acceptExpiresAt(20).build();
        try{
            return verifier.verify(token);
        }
        catch (Exception e)
        {
            throw GenericException.builder().httpStatus(HttpStatus.BAD_REQUEST).errorMessage(e.getMessage()).build();
        }

    }
}
