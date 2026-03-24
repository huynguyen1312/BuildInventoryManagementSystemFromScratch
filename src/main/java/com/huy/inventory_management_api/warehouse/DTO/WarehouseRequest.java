package com.huy.inventory_management_api.warehouse.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WarehouseRequest {
    @NotBlank(message = "Warehouse code must not be blank")
    @Size(max = 50, message = "Warehouse code must not exceed 50 characters")
    private String code;

    @NotBlank(message = "Warehouse name must not be blank")
    @Size(max = 150, message = "Warehouse name must not exceed 150 characters")
    private String name;

    @Size(max = 255, message = "Location must not exceed 255 characters")
    private String location;
}
