package com.fsemart.entity;

import java.util.List;

public record CategoryUserDto(
        Long id,
        String name ,
        List<ProductUserDto> produktet
) {}