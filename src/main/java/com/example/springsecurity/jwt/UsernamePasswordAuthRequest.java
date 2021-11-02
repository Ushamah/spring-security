package com.example.springsecurity.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UsernamePasswordAuthRequest {
    private String username;
    private String password;
}
