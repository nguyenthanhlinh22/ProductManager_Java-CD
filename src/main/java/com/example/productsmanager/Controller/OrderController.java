package com.example.productsmanager.Controller;

import com.example.productsmanager.Model.Order;
import com.example.productsmanager.Model.Product; // Thêm import cho Product
import com.example.productsmanager.Model.ProductType;
import com.example.productsmanager.Service.OrderService;
import com.example.productsmanager.Service.ProductService; // Thêm import cho ProductService
import com.example.productsmanager.Service.ProductTypeService;
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
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService; // Khai báo ProductService
// Khai báo ProductService

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
        List<ProductType> productTypes = productTypeService.getAllProductTypes();

        model.addAttribute("order", order);
        model.addAttribute("productTypes", productTypes);

        return "editOrder"; // View sẽ là editOrder.html
    }




    @PostMapping("/update")
    public String updateOrder(@ModelAttribute Order order) {
        Product product = productService.findById(order.getSanPham().getMaSp()); // Tìm sản phẩm theo ID
        if (product == null) {
            System.out.println("Sản phẩm không tồn tại!");
            return "redirect:/orders?error=Sản phẩm không tồn tại!";
        }
        order.setSanPham(product);
        orderService.updateOrder(order.getMaDonHang(), order);
        System.out.println("Cập nhật thành công, chuyển hướng về danh sách đơn hàng.");
        return "redirect:/orders";
    }



    @GetMapping("/byType/{maLoaiSp}")
    @ResponseBody
    public List<Product> getProductsByType(@PathVariable Long maLoaiSp) {
        return productService.getProductsByType(maLoaiSp);
    }

}
