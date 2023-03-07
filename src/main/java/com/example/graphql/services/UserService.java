package com.example.graphql.services;

import com.example.graphql.entity.User;
import com.example.graphql.exceptions.UserNotFoundException;
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
        return optionalUser.orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(String firstName, String lastName, int age, String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setEmail(email);
        return userRepository.save(user);
    }
}
