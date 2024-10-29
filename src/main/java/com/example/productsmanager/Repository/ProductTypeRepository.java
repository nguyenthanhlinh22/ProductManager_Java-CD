package com.example.productsmanager.Repository;

import com.example.productsmanager.Model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

}
