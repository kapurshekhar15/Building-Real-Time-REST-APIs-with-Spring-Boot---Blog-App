package com.spirngboot.blog.Spring.service;

import com.spirngboot.blog.Spring.Payload.LoginDto;
import com.spirngboot.blog.Spring.Payload.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
