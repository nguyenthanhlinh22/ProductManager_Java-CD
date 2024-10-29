package com.example.productsmanager.Service;

import com.example.productsmanager.Model.ProductType;
import com.example.productsmanager.Repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }
}

