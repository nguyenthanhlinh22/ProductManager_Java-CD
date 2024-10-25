package com.example.productsmanager.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "loai_san_pham") // Đảm bảo tên bảng là chính xác
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maLoaiSp; // Đảm bảo trường này là khóa chính

    private String tenLoaiSp; // Tên loại sản phẩm

    // Getter và Setter
    public Long getMaLoaiSp() {
        return maLoaiSp;
    }

    public void setMaLoaiSp(Long maLoaiSp) {
        this.maLoaiSp = maLoaiSp;
    }

    public String getTenLoaiSp() {
        return tenLoaiSp;
    }

    public void setTenLoaiSp(String tenLoaiSp) {
        this.tenLoaiSp = tenLoaiSp;
    }
}
