package com.huy.inventory_management_api.stockmovement;

import com.huy.inventory_management_api.stockmovement.DTO.StockMovementResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @GetMapping
    public List<StockMovementResponse> getAllStockMovements() {
        return stockMovementService.getAllStockMovements();
    }

    @GetMapping("/{id}")
    public StockMovementResponse getStockMovementById(@PathVariable Long id) {
        return stockMovementService.getStockMovementById(id);
    }

    @GetMapping("/product/{productId}")
    public List<StockMovementResponse> getStockMovementsByProductId(@PathVariable Long productId) {
        return stockMovementService.getStockMovementsByProductId(productId);
    }

    @GetMapping("/warehouse/{warehouseId}")
    public List<StockMovementResponse> getStockMovementsByWarehouseId(@PathVariable Long warehouseId) {
        return stockMovementService.getStockMovementsByWarehouseId(warehouseId);
    }

    @GetMapping("/product/{productId}/warehouse/{warehouseId}")
    public List<StockMovementResponse> getStockMovementsByProductAndWarehouse(@PathVariable Long productId,
                                                                              @PathVariable Long warehouseId) {
        return stockMovementService.getStockMovementsByProductIdAndWarehouseId(productId, warehouseId);
    }
}