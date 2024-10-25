package com.example.productsmanager.Service;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message); // Gọi constructor của lớp cha với thông điệp
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause); // Gọi constructor của lớp cha với thông điệp và nguyên nhân
    }
}