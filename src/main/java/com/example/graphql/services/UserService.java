package com.example.graphql.services;

import com.example.graphql.entity.User;
import com.example.graphql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }


}
