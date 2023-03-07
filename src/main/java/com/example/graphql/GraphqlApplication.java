package com.example.graphql;

import com.example.graphql.entity.Subscription;
import com.example.graphql.entity.User;
import com.example.graphql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import net.datafaker.service.FakeValuesService;
import net.datafaker.service.FakerContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
@RequiredArgsConstructor
public class GraphqlApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    private final Faker faker;

    private final Random random;

    private final FakeValuesService fakeValuesService;

    private final FakerContext fakerContext;

    public static void main(String[] args) {
        SpringApplication.run(GraphqlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(random.nextInt(15, 98));
            user.setEmail(fakeValuesService.bothify("????##@gmail.com", fakerContext));
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            for (int j = 0; j < random.nextInt(1, 10); j++) {
                Subscription subscription = new Subscription();
                subscription.setName(faker.name().title());
                user.addSubscription(subscription);
            }
            userRepository.save(user);
        }
    }

}
