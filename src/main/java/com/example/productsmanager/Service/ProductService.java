package com.example.productsmanager.Service;

import com.example.productsmanager.Model.Product;
import com.example.productsmanager.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // Lấy tất cả sản phẩm
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Tìm sản phẩm theo tên
    public Product findByName(String tenSp) {
        return productRepository.findByTenSp(tenSp).orElse(null); // Hoặc throw một exception nếu không tìm thấy
    }

    // Thêm sản phẩm mới
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElse(null); // Hoặc throw một exception nếu không tìm thấy
    }

    // Cập nhật sản phẩm
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        existingProduct.setTenSp(updatedProduct.getTenSp());
        existingProduct.setGiaSp(updatedProduct.getGiaSp());
        existingProduct.setTinhTrangSp(updatedProduct.getTinhTrangSp()); // Cập nhật tình trạng sản phẩm
        existingProduct.setLoaiSanPham(updatedProduct.getLoaiSanPham()); // Cập nhật loại sản phẩm

        return productRepository.save(existingProduct);
    }

    // Xóa sản phẩm
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // ProductService.java
    public List<Product> getProductsByType(Long maLoaiSp) {
        return productRepository.findByLoaiSanPham_MaLoaiSp(maLoaiSp);
    }



}