package com.example.graphql.controller;

import com.example.graphql.entity.User;
import com.example.graphql.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * The same thins with @QueryMapping
     *
     * @return UserDto
     */
    @SchemaMapping(typeName = "Query", field = "user")
    public User user(@Argument long id) {
        return userService.getUser(id);
    }

    @SchemaMapping(typeName = "Query", field = "users")
    public List<User> users() {
        return userService.getUsers();
    }

    @MutationMapping
    public User addUser(@Argument String firstName, @Argument String lastName,
                        @Argument int age, @Argument String email) {
        return userService.addUser(firstName, lastName, age, email);
    }

}
