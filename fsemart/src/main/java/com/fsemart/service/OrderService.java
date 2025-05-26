package com.fsemart.service;

import com.fsemart.entity.Order;
import com.fsemart.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public Order bejPorosi(Long userId, List<OrderItem> items);


    Optional<Order> getOrdersByUser(Long userId);

    List<Order> getAllOrders();

}