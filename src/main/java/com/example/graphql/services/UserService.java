package com.example.graphql.services;

import com.example.graphql.entity.Subscription;
import com.example.graphql.entity.User;
import com.example.graphql.repository.SubscriptionRepository;
import com.example.graphql.repository.UserRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.*;

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

    public Mono<Map<User, Set<Subscription>>> getBatchUser(List<User> users) {
        Set<Subscription> subscriptionsByUser = subscriptionRepository.findByUserIn(users);
        Map<User, Set<Subscription>> result = new HashMap<>();
        subscriptionsByUser.forEach(subscription -> {
            if (result.containsKey(subscription.getUser())) {
                result.get(subscription.getUser()).add(subscription);
            } else {
                result.put(subscription.getUser(), new HashSet<>(List.of(subscription)));
            }
        });
        return Mono.just(result);
    }

}
