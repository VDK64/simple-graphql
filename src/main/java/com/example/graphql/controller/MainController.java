package com.example.graphql.controller;

import com.example.graphql.dto.UserDto;
import com.example.graphql.services.UserService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {

    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping("user")
    UserDto someUSer() {
        return userService.getUser();
    }

}
