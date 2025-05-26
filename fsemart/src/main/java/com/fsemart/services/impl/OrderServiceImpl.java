package com.fsemart.services.impl;


import com.fsemart.entity.Order;
import com.fsemart.entity.OrderItem;
import com.fsemart.entity.Product;
import com.fsemart.entity.User;
import com.fsemart.repository.OrderRepository;
import com.fsemart.repository.ProductRepository;
import com.fsemart.repository.UserRepository;
import com.fsemart.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public Order bejPorosi(Long userId, List<OrderItem> items){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User nuk u gjet"));
        Order order = new Order();

        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();

        double total = 0;

        for (OrderItem item : items) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Produkti nuk u gjet"));

            item.setProduct(product);
            item.setPrice(product.getPrice());
            item.setOrder(order);

            total += product.getPrice() * item.getQuantity() ;

            orderItems.add(item);

        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(total);

        orderRepository.save(order);


        return order;
    }

    @Override
    public Optional<Order> getOrdersByUser(Long userId) {
        return orderRepository.findById(userId);

    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


}
