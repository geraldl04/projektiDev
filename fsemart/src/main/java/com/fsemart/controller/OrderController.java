package com.fsemart.controller;

import com.fsemart.entity.Order;
import com.fsemart.entity.OrderItem;
import com.fsemart.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/{userId}")
    public Order placeOrder(@PathVariable Long userId, @RequestBody List<OrderItem> items) {
        return orderService.bejPorosi(userId, items);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{userId}")
    public Optional<Order> getUserOrders(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}

