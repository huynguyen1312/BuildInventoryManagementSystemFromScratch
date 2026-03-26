package com.huy.inventory_management_api.inventory.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class StockInRequest {

    @NotNull(message = "Product id must not be null")
    private Long productId;

    @NotNull(message = "Warehouse id must not be null")
    private Long warehouseId;

    @NotNull(message = "Quantity must not be null")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;

    @Size(max = 255, message = "Note must not exceed 255 characters")
    private String note;
}
