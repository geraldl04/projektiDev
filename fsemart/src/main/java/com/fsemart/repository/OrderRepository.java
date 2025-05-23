package com.fsemart.repository;

import com.fsemart.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findById(Long id ) ;
    //gjej te gjithe entitete order ku user id ka kete id , spring data jpa query method
}
