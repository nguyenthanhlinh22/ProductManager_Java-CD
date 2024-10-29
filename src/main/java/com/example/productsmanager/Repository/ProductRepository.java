package com.example.productsmanager.Repository;

import com.example.productsmanager.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByTenSp(String tenSp);

    // Thêm phương thức tìm kiếm sản phẩm theo mã loại sản phẩm qua ProductType
    List<Product> findByLoaiSanPham_MaLoaiSp(Long maLoaiSp);
}
