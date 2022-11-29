package com.example.tmpelectronicsn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ownersName;
    private String verificationCode;
    private Date expirationDate;
    private String crCardNumber;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

}
