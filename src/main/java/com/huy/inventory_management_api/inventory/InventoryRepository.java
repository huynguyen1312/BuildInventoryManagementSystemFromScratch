package com.huy.inventory_management_api.inventory;

import org.springframework.data.jpa.repository.JpaRepository;  
  
import java.util.Optional;  
  
public interface InventoryRepository extends JpaRepository<Inventory, Long> {  
    Optional<Inventory> findByProductIdAndWarehouseId(Long productId, Long warehouseId);  
}
