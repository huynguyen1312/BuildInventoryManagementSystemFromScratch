package com.huy.inventory_management_api.inventory;

import com.huy.inventory_management_api.inventory.DTO.InventoryResponse;
import com.huy.inventory_management_api.inventory.DTO.StockInRequest;
import com.huy.inventory_management_api.inventory.DTO.StockOutRequest;

import java.util.List;
public interface InventoryService {

    InventoryResponse getInventoryById(Long id);

    InventoryResponse getInventoryByProductAndWarehouse(Long productId, Long warehouseId);

    List<InventoryResponse> getAllInventories();

    List<InventoryResponse> getInventoriesByProductId(Long productId);

    List<InventoryResponse> getInventoriesByWarehouseId(Long warehouseId);

    InventoryResponse stockIn(StockInRequest request);

    InventoryResponse stockOut(StockOutRequest request);
}
