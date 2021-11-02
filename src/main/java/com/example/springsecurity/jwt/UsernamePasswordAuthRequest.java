package com.example.springsecurity.jwt;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
public class UsernamePasswordAuthRequest {
    private String username;
    private String password;
}
