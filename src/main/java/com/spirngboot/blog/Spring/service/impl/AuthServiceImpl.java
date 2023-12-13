package com.spirngboot.blog.Spring.service.impl;

import com.spirngboot.blog.Spring.Payload.LonginDto;
import com.spirngboot.blog.Spring.service.AuthService;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;

    public AuthServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(LonginDto longinDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                longinDto.getUsernameOrEmail(), longinDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User Logged in successfully!.";
    }
}
