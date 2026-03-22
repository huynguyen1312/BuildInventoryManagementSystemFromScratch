package com.huy.inventory_management_api.product.DTO;

import java.time.LocalDateTime;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private String sku;
    private String unit;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
