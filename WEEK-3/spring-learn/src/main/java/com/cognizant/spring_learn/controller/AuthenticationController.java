package com.cognizant.spring_learn.controller;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import com.cognizant.spring_learn.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

@RestController
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

//    @GetMapping("/authenticate")
//    public Map<String, String> authenticate(Authentication authentication) {
//
//        String token = jwtUtil.generateToken(authentication.getName());
//
//        return Collections.singletonMap("token", token);
//    }

    private static final Logger LOGGER =
            LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(
            @RequestHeader("Authorization") String authHeader) {

        LOGGER.info("START");

        LOGGER.debug("Authorization Header : {}", authHeader);

        String user = getUser(authHeader);

        LOGGER.debug("User : {}", user);

        String token = generateJwt(user);

        LOGGER.debug("JWT Token : {}", token);

        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        LOGGER.info("END");

        return map;
    }

    private String getUser(String authHeader) {

        LOGGER.debug("Start of getUser()");

        // Remove "Basic "
        String encodedCredentials = authHeader.substring(6);

        LOGGER.debug("Encoded Credentials : {}", encodedCredentials);

        // Decode Base64
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);

        String credentials = new String(decodedBytes);

        LOGGER.debug("Decoded Credentials : {}", credentials);

        // Extract username
        String user = credentials.substring(0, credentials.indexOf(":"));

        LOGGER.debug("Username : {}", user);

        LOGGER.debug("End of getUser()");

        return user;
    }
    private String generateJwt(String user) {

        LOGGER.debug("Start generateJwt()");

        JwtBuilder builder = Jwts.builder();

        builder.setSubject(user);

        // Issue time
        builder.setIssuedAt(new Date());

        // Expiry time (20 minutes)
        builder.setExpiration(new Date(new Date().getTime() + 1200000));

        // Sign with secret key
        builder.signWith(SignatureAlgorithm.HS256,
                "mysecretkeymysecretkeymysecretkey1234567891028");

        String token = builder.compact();

        LOGGER.debug("Generated Token : {}", token);

        LOGGER.debug("End generateJwt()");

        return token;
    }
}
