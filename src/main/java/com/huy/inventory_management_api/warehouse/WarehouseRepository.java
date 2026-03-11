package com.huy.inventory_management_api.warehouse;
  
import org.springframework.data.jpa.repository.JpaRepository;  
  
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {  
    boolean existsByCode(String code);  
}