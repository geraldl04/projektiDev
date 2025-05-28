package com.fsemart.entity;

import java.util.List;

public record OrderDto(Long id , List<OrderItemDto> orderItemsDtos , double shumaTotale) {
}
