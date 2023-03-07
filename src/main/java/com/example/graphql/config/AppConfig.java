package com.example.graphql.config;

import net.datafaker.Faker;
import net.datafaker.service.FakeValuesService;
import net.datafaker.service.FakerContext;
import net.datafaker.service.RandomService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;
import java.util.Random;

@Configuration
public class AppConfig {

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public FakeValuesService fakeValuesService() {
        return new FakeValuesService();
    }

    @Bean
    public FakerContext fakerContext() {
        return new FakerContext(Locale.ENGLISH, new RandomService());
    }

}
