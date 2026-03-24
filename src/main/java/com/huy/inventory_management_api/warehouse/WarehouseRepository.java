package com.huy.inventory_management_api.warehouse;
  
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;  
  
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {  
    boolean existsByCode(String code);  

    Optional<Warehouse> findByCode(String code);
}