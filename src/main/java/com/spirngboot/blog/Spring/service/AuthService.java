package com.spirngboot.blog.Spring.service;

import com.spirngboot.blog.Spring.Payload.LonginDto;

public interface AuthService {
    String login(LonginDto longinDto);
}
