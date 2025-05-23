package com.fsemart.controller;

import com.fsemart.entity.Order;
import com.fsemart.entity.OrderItem;
import com.fsemart.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{userId}")
    public Order placeOrder(@PathVariable Long userId, @RequestBody List<OrderItem> items) {
        return orderService.bejPorosi(userId, items);
    }

    @GetMapping("/test")
    public String test() {
        return "Order controller is working!";
    }

    @GetMapping("/user/{userId}")
    public Optional<Order> getUserOrders(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}

