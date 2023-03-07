package com.example.graphql.services;

import com.example.graphql.entity.Subscription;
import com.example.graphql.entity.User;
import com.example.graphql.repository.SubscriptionRepository;
import com.example.graphql.repository.UserRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserService {
    private final UserRepository userRepository;

    private final SubscriptionRepository subscriptionRepository;

    public UserService(UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public User getUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Mono<Map<User, List<Subscription>>> getBatchUser(List<User> users) {
        Mono<Map<User, List<Subscription>>> just = Mono.just(users.stream()
                .collect(Collectors.toMap(Function.identity(), subscriptionRepository::findByUser)));
        System.out.println();
        return just;
    }

}
