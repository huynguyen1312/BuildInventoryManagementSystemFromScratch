package com.huy.inventory_management_api.inventory;

import com.huy.inventory_management_api.inventory.DTO.InventoryResponse;
import com.huy.inventory_management_api.inventory.DTO.StockInRequest;
import com.huy.inventory_management_api.inventory.DTO.StockOutRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/stock-in")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse stockIn(@Valid @RequestBody StockInRequest request) {
        return inventoryService.stockIn(request);
    }

    @PostMapping("/stock-out")
    public InventoryResponse stockOut(@Valid @RequestBody StockOutRequest request) {
        return inventoryService.stockOut(request);
    }

    @GetMapping
    public List<InventoryResponse> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @GetMapping("/{id}")
    public InventoryResponse getInventoryById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id);
    }

    @GetMapping("/product/{productId}/warehouse/{warehouseId}")
    public InventoryResponse getInventoryByProductAndWarehouse(@PathVariable Long productId,
                                                               @PathVariable Long warehouseId) {
        return inventoryService.getInventoryByProductAndWarehouse(productId, warehouseId);
    }

    @GetMapping("/product/{productId}")
    public List<InventoryResponse> getInventoriesByProductId(@PathVariable Long productId) {
        return inventoryService.getInventoriesByProductId(productId);
    }

    @GetMapping("/warehouse/{warehouseId}")
    public List<InventoryResponse> getInventoriesByWarehouseId(@PathVariable Long warehouseId) {
        return inventoryService.getInventoriesByWarehouseId(warehouseId);
    }
}
