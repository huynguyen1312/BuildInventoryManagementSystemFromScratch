package com.huy.inventory_management_api.stockmovement.DTO;

import com.huy.inventory_management_api.stockmovement.MovementType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class StockMovementResponse {
    private Long id;
    private Long productId;
    private String productSku;
    private String productName;
    private Long warehouseId;
    private String warehouseCode;
    private String warehouseName;
    private MovementType type;
    private Integer quantity;
    private String note;
    private LocalDateTime createdAt;
}
