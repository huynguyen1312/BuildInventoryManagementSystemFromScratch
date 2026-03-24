package com.huy.inventory_management_api.warehouse.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class WarehouseResponse {
    private Long id;
    private String code;
    private String name;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
