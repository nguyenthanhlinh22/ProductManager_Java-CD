package com.example.productsmanager.Service;

import com.example.productsmanager.Model.Order;
import com.example.productsmanager.Model.Product;
import com.example.productsmanager.Repository.OrderRepository;
import com.example.productsmanager.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository; // Đã thêm @Autowired

    @Autowired
    private ProductService productService;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public String validateAndFetchOrders(String startDateStr, String endDateStr, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start;
        LocalDate end;

        try {
            start = LocalDate.parse(startDateStr, formatter);
            end = LocalDate.parse(endDateStr, formatter);
        } catch (DateTimeParseException e) {
            return "Định dạng ngày không đúng. Vui lòng nhập theo định dạng yyyy-MM-dd.";
        }

        if (start.isBefore(LocalDate.now())) {
            return "Ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại.";
        }

        if (!end.isAfter(start)) {
            return "Ngày kết thúc phải lớn hơn ngày bắt đầu.";
        }

        List<Order> orders = orderRepository.findByNgayMuaBetween(start.atStartOfDay(), end.atTime(23, 59, 59));
        model.addAttribute("orders", orders);
        return null; // Không có lỗi
    }

    public List<Order> getTopOrders(int topCount) {
        List<Order> orders = orderRepository.findAll();
        orders.sort((o1, o2) -> {
            double total1 = o1.getSanPham().getGiaSp() * o1.getSoLuong();
            double total2 = o2.getSanPham().getGiaSp() * o2.getSoLuong();
            return Double.compare(total2, total1);
        });
        return orders.stream().limit(topCount).toList(); // Trả về topCount đơn hàng
    }

    public Order updateOrder(Long orderId, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Đơn hàng không tồn tại"));

        // Kiểm tra và lấy sản phẩm từ ProductService
        Product product = productService.findByName(updatedOrder.getSanPham().getTenSp());
        if (product == null) {
            throw new RuntimeException("Sản phẩm không tồn tại."); // Thông báo nếu không tìm thấy sản phẩm
        }

        // Cập nhật thông tin đơn hàng
        existingOrder.setSanPham(product);
        existingOrder.setNgayMua(updatedOrder.getNgayMua());
        existingOrder.setSoLuong(updatedOrder.getSoLuong());

        return orderRepository.save(existingOrder);
    }

    // Thêm phương thức getOrderById
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Đơn hàng không tồn tại"));
    }

    public Product findByName(String productName) {
        return productRepository.findByTenSp(productName)
                .orElse(null); // Hoặc throw một exception nếu không tìm thấy
    }
}
