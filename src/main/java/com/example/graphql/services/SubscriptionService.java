package com.example.graphql.services;

import com.example.graphql.entity.Subscription;
import com.example.graphql.entity.User;
import com.example.graphql.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public Mono<Map<User, Set<Subscription>>> getSubscriptionsByUsers(List<User> users) {
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
