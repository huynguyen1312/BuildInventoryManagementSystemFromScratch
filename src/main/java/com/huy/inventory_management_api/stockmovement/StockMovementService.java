package com.huy.inventory_management_api.stockmovement;

import com.huy.inventory_management_api.stockmovement.DTO.StockMovementResponse;

import java.util.List;

public interface StockMovementService {
    List<StockMovementResponse> getAllStockMovements();
    StockMovementResponse getStockMovementById(Long id);
    List<StockMovementResponse> getStockMovementsByProductId(Long productId);
    List<StockMovementResponse> getStockMovementsByWarehouseId(Long warehouseId);
    List<StockMovementResponse> getStockMovementsByProductIdAndWarehouseId(Long productId, Long warehouseId);
}
