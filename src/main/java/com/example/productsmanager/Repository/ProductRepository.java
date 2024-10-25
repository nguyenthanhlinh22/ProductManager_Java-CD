package com.example.productsmanager.Repository;

import com.example.productsmanager.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Tìm sản phẩm theo tên
    Optional<Product> findByTenSp(String tenSp);


}
