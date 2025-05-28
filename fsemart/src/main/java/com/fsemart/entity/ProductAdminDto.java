package com.fsemart.entity;

//rekorde jan tipe te dhenash immmutable  qe kerkojne vetem tipin dhe emrin e fushes
//shembull public record Person (String name, String address) {}
//ka getter setter konstruktor equals ,tostring , hashcode
public record ProductAdminDto(
        Long id,
        String title,
        String description,
        double price,
        int stock,
        String imageUrl,
        Long categoryId,
        String emriShitesit,
        String emailiShtesit
) {}