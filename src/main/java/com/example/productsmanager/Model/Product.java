package com.example.productsmanager.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "san_pham") // Đảm bảo tên bảng là chính xác
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maSp; // Đây là trường khóa chính

    private String tenSp; // Tên sản phẩm
    private Double giaSp; // Giá sản phẩm
    private String tinhTrangSp; // Tình trạng sản phẩm

    @ManyToOne
    @JoinColumn(name = "ma_loai_sp", referencedColumnName = "maLoaiSp") // Kiểm tra lại ánh xạ này
    private ProductType loaiSanPham;

    // Getter và Setter

    public Long getMaSp() {
        return maSp;
    }

    public void setMaSp(Long maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public Double getGiaSp() {
        return giaSp;
    }

    public void setGiaSp(Double giaSp) {
        this.giaSp = giaSp;
    }

    public String getTinhTrangSp() {
        return tinhTrangSp;
    }

    public void setTinhTrangSp(String tinhTrangSp) {
        this.tinhTrangSp = tinhTrangSp;
    }

    public ProductType getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(ProductType loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }
}
