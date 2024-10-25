package com.example.productsmanager.Controller;

import com.example.productsmanager.Model.Order;
import com.example.productsmanager.Model.Product; // Thêm import cho Product
import com.example.productsmanager.Service.OrderService;
import com.example.productsmanager.Service.ProductService; // Thêm import cho ProductService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService; // Khai báo ProductService

    // Hiển thị danh sách tất cả các đơn hàng
    @GetMapping
    public String listOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "OrderList"; // Trả về view orderList.html
    }

    // Lọc đơn hàng theo khoảng thời gian
    @GetMapping("/filter")
    public String filterOrders(@RequestParam String startDate, @RequestParam String endDate, Model model) {
        // Gọi phương thức validateAndFetchOrders để xác thực và lấy đơn hàng
        String error = orderService.validateAndFetchOrders(startDate, endDate, model);

        if (error != null) {
            model.addAttribute("error", error); // Thêm thông báo lỗi vào model
            return "OrderList"; // Trả về trang danh sách đơn hàng
        }

        return "OrderList"; // Trả về trang danh sách đơn hàng đã lọc
    }

    @GetMapping("/top")
    public String topOrders(@RequestParam int topCount, Model model) {
        List<Order> topOrders = orderService.getTopOrders(topCount);
        model.addAttribute("orders", topOrders);
        return "orderList"; // Trả về trang danh sách đơn hàng
    }

    @GetMapping("/edit")
    public String editOrder(@RequestParam Long maDonHang, Model model) {
        Order order = orderService.getOrderById(maDonHang);
        model.addAttribute("order", order);
        return "editOrder";
    }

    @PostMapping("/update")
    public String updateOrder(@ModelAttribute Order order) {
        // Tìm sản phẩm dựa trên tên sản phẩm
        Product product = productService.findByName(order.getSanPham().getTenSp());

        if (product == null) {
            // Nếu sản phẩm không tồn tại, trả về thông báo lỗi
            return "redirect:/orders?error=Sản phẩm không tồn tại!";
        }

        // Gán sản phẩm đã tìm thấy cho đơn hàng
        order.setSanPham(product);

        // Cập nhật thông tin đơn hàng
        orderService.updateOrder(order.getMaDonHang(), order);

        return "redirect:/orders"; // Chuyển hướng về danh sách đơn hàng
    }
}
