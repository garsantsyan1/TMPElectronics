package com.example.tmpelectronicsn.service;


import com.example.tmpelectronicsn.entity.Role;
import com.example.tmpelectronicsn.entity.User;
import com.example.tmpelectronicsn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(User user) {
            String encode = passwordEncoder.encode(user.getPassword());
            user.setPassword(encode);
            user.setRole(Role.USER);
            userRepository.save(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByToken(UUID fromString) {
        return userRepository.findByToken(fromString);
    }
    public Optional<User> findByToken(String fromString) {
        return userRepository.findByToken(fromString);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
