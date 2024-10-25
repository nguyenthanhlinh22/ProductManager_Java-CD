package com.example.productsmanager.Repository;

import com.example.productsmanager.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
    List<Order> findByNgayMuaBetween(LocalDateTime startDate, LocalDateTime endDate);
}
