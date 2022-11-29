package com.example.tmpelectronicsn.repository;

import com.example.tmpelectronicsn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Integer> {


    List<User> findAllByName(String name);

    Optional<User> findByEmail(String email);


    Optional<User> findByToken(UUID token);

    Optional<User> findByToken(String token);

}
