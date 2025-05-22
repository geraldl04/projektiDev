package com.fsemart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 50)
    private String title;

    @Column(length = 1000)
    private String description;


    @Column(nullable = false )
    private double price;

    @Column(nullable = false)
    private int stock;


    @Column(length = 500)
    private String imageUrl;

    @ManyToOne //do thote qe cdo produkt lidhet me 1 user(admin) por 1 admin mund te kete disa produkte
    @JoinColumn(name = "seller_id") //lidh seller_id me id e userit
    private User seller;

    @ManyToOne //nje produkt i perket nje kategorie por nje kategori ka disa produkte
    @JoinColumn(name="category_id")
    @JsonBackReference  //vendoset per te treguar qe referenca e prindit te injorohet qe mos tkem loop t perhershem
    private Category category;

    private LocalDateTime createdAt = LocalDateTime.now();
}
