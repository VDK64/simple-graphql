package com.example.graphql.services;

import com.example.graphql.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    public UserDto getUser() {
        return new UserDto(1L, "John", 32);
    }
}
