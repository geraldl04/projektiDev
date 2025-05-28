package com.fsemart.entity;

import java.util.List;

public record CategoryAdminDto(
        Long id,
        String name ,
        List<ProductAdminDto> produktet)
{

}
