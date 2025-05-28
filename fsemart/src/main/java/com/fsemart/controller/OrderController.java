package com.fsemart.controller;

import com.fsemart.entity.*;
import com.fsemart.service.OrderService;
import com.fsemart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderService orderService;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/{userId}")
    public Object placeOrder(@PathVariable Long userId, @RequestBody List<OrderItem> items) {
//pyetja qe shtrohet ? Kush mund te beje nje porosi ? Per arsye demonstrative kam bere qe admini te mund te beje porosi per
        //te gjithe userat ndersa nje user te beje vetem per vete .
        //pra ideja : nuk lejohet te behet porosi kur User eshte user pra jo admin dhe kur nuk pepruthet Id e useri-t
        //te autentikuar me id e userit qe kerkon te bej porosine
        var auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));


        //get principal me kthen detajet e userit te autentikuar
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Long currentUserId = userDetails.getUserEntity().getId();

        if (!isAdmin && !userId.equals(currentUserId)) {
            System.out.println("Nuk mund te besh porosi per nje user tjeter");
            throw new AccessDeniedException(" Nuk mund te besh porosi per nje user tjeter ");
        }

        Order order = orderService.bejPorosi(userId, items);

        if(!isAdmin) {
            System.out.println(" U kthye ne order dto ");
            return ktheOrderNeDto(order);


        }
         return order ;


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



    //perdoret e njejta logjike si ne produkt si ne kategori
    public OrderDto ktheOrderNeDto(Order order){
        List<OrderItemDto> orderItemDtos = order.getOrderItems().stream()
                .map(item -> new OrderItemDto(
                        item.getId(),
                        new ProductUserDto(
                                item.getProduct().getId(),
                                item.getProduct().getTitle(),
                                item.getProduct().getDescription(),
                                item.getProduct().getPrice(),
                                item.getProduct().getStock(),
                                item.getProduct().getImageUrl() ,
                                item.getProduct().getCategory().getId()
                        ),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .toList();

        return new OrderDto(order.getId(), orderItemDtos, order.getTotalAmount());

    }

}

