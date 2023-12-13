package com.spirngboot.blog.Spring.Payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LonginDto {
    private String usernameOrEmail;
    private String password;
}
