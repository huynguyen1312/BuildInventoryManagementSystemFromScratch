package com.huy.inventory_management_api.inventory.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class InventoryResponse {

    private Long id;
    private Long productId;
    private String productSku;
    private String productName;
    private Long warehouseId;
    private String warehouseCode;
    private String warehouseName;
    private Integer quantity;
    private LocalDateTime updatedAt;

}
