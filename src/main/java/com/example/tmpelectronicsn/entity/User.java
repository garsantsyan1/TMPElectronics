package com.example.tmpelectronicsn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String token;
    private boolean active;
    private LocalDateTime tokenCreatedDate;
}
