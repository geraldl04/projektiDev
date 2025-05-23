package com.fsemart.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.* ;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")

//ideja eshte qe nje order ti perkase nje useri por nje user te kete disa orders , cmimin total , dhe nje liste me
//me order items
public class Order {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user ;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING ;

    //nuk e bej dot kshu se do me duhet te shtoja cdo produkt manualisht psh nese nr i prod qe dua eshte 4
    //pr te njejtin produkt duhet ta shtoj 4 here , jo eficente
    //private List<Product> listaProdukteve ;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItem> orderItems ;


    private LocalDateTime createdAt = LocalDateTime.now();

    public enum OrderStatus {
        PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    }

    private double totalAmount;


}
