package com.example.graphql.controller;

import com.example.graphql.entity.Subscription;
import com.example.graphql.entity.User;
import com.example.graphql.services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @BatchMapping(typeName = "User", field = "subscriptions")
    public Mono<Map<User, Set<Subscription>>> subscriptions(List<User> users) {
        return subscriptionService.getSubscriptionsByUsers(users);
    }

}
