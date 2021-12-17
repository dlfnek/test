package com.example.test.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class UserDto {

    private final String token;
    private final String username;
    private final String password;
}
