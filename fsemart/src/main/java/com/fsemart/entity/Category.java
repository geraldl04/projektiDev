package com.fsemart.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.* ;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable=false , length=50)
    private String name ;

    @Column(nullable=false , length=200)
    private String description ;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference //vendoset tek prindi per te treguar qe eshte prindi ne lidhjet "2 drejtimore"
    private List<Product> products;
    //mappedBy = "category" i thote Jpa  fusha kategori ne product mban lidhjen , kshtu shmang krijijmin
    //e nje tabele tjt // kur thirret i thote shiko te kjo id te tabela e produkteve kush e ka dhe mi jep kto produkte

}
