package com.spirngboot.blog.Spring.controller;

import com.spirngboot.blog.Spring.Payload.LonginDto;
import com.spirngboot.blog.Spring.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Buil Login REST API or Sign API
    @PostMapping(value = {"/login", "/singin"}) // here client can user both either singin or login
    public ResponseEntity<String> login(@RequestBody LonginDto longinDto) {
        String response = authService.login(longinDto);
        return ResponseEntity.ok(response);
    }


}
