package com.huy.inventory_management_api.warehouse;

import com.huy.inventory_management_api.warehouse.DTO.*;
import java.util.List;
public interface WarehouseService {

    WarehouseResponse createWarehouse(WarehouseRequest request);

    List<WarehouseResponse> getAllWarehouses();

    WarehouseResponse getWarehouseById(Long id);

    WarehouseResponse updateWarehouse(Long id, WarehouseRequest request);

    void deleteWarehouse(Long id);
}
