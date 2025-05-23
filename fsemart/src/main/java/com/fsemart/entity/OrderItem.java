package com.fsemart.entity;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.* ;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
//order items do kete nje produkt , do mbaj si reference cili order e ka kete order item , do kete sasine dhe cmimin
//ne momentin e orderit
@Entity
@Table(name = "order_items")
@Data @NoArgsConstructor @AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    private int quantity;

    private double price; //e mbajm price per arsye se nqs admini ndryshon cmimin un te mbaj si reference
    //cmimin ne momentin qe kam bere order-in.

}
