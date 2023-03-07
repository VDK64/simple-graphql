package com.example.graphql.controller;

import com.example.graphql.entity.Subscription;
import com.example.graphql.entity.User;
import com.example.graphql.services.SubscriptionService;
import com.example.graphql.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    private final SubscriptionService subscriptionService;

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


    @BatchMapping(typeName = "User", field = "subscriptions")
    public Mono<Map<User, Set<Subscription>>> subscriptions(List<User> users) {
        return subscriptionService.getSubscriptionsByUsers(users);
    }

    @MutationMapping
    public User addUser(@Argument String firstName, @Argument String lastName,
                        @Argument int age, @Argument String email) {
        return userService.addUser(firstName, lastName, age, email);
    }

}
