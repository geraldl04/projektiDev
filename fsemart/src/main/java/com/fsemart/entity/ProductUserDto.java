package com.fsemart.entity;

public record ProductUserDto(
        Long id,
        String title,
        String description,
        double price,
        int stock,
        String imageUrl,
        Long categoryId
) {
}