package com.fsemart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


//do me mbaj dhe user dhe admin qe do i perdor me von per fshirje produktesh userash , askese te vecanta etj
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

//si fillim nje user ka emrin , emailin , passwordin , rolin qe me duhet me vone dhe nje creationTime

   @Id //trajtohen automatikisht si kolone , dhe nqs si percak emer kolone  , primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)//nga kolona identity eshte auto incremented
   private Long id ;


   @NotBlank(message = " Duhet te vendosesh username")
   @Column(nullable = false , length = 255)
   private String username ;


   @Email(message = "Email i pavlefshem")
   @NotBlank(message = "Email-i nuk duhet te jete bosh")
   @Column(unique = true , nullable = false , length = 255)
   private String email ;


   @NotBlank(message = "Password-i nuk duhet te jete bosh")
   @Column(nullable = false , length = 255)
   private String password ;

   private LocalDateTime createdAt = LocalDateTime.now();


    @Enumerated(EnumType.STRING)  //specifikon tipin se si do behet store ne database (ordinal ose string)
    @Column(name = "role", nullable = false)
    private Role role = Role.USER;

    public enum Role {
        ADMIN, USER
    }
}
