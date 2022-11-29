package com.example.tmpelectronicsn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String color;
    private String description;
    private int count;
    private double weightKg;
    private String model;
    private String brand;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Orders orders;

}
