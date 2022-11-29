package com.example.tmpelectronicsn.repository;

import com.example.tmpelectronicsn.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}
