package com.huy.inventory_management_api.warehouse;

import com.huy.inventory_management_api.common.exception.DuplicateResourceException;
import com.huy.inventory_management_api.common.exception.ResourceNotFoundException;
import com.huy.inventory_management_api.warehouse.DTO.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public WarehouseResponse createWarehouse(WarehouseRequest request) {
        // Implementation for creating a warehouse
        if(warehouseRepository.existsByCode(request.getCode())) {
            throw new DuplicateResourceException("Warehouse code already exists: " + request.getCode());
        }
        Warehouse warehouse = Warehouse.builder()
                .code(request.getCode())
                .name(request.getName())
                .location(request.getLocation())
                .build();
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return mapToWarehouseResponse(savedWarehouse);
    }

    @Override
    public List<WarehouseResponse> getAllWarehouses() {
        // Implementation for retrieving all warehouses
        List<Warehouse> warehouses = warehouseRepository.findAll();

        return warehouses.stream()
                .map(this::mapToWarehouseResponse)
                .toList();
    }

    @Override
    public WarehouseResponse getWarehouseById(Long id) {
        // Implementation for retrieving a warehouse by ID
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with id: " + id));

        return mapToWarehouseResponse(warehouse);
    }

    @Override
    public WarehouseResponse updateWarehouse(Long id, WarehouseRequest request) {
        // Implementation for updating a warehouse
        Warehouse existingWarehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with id: " + id));

        if (!existingWarehouse.getCode().equals(request.getCode())
                && warehouseRepository.existsByCode(request.getCode())) {
            throw new DuplicateResourceException("Warehouse code already exists: " + request.getCode());
        }

        existingWarehouse.setCode(request.getCode());
        existingWarehouse.setName(request.getName());
        existingWarehouse.setLocation(request.getLocation());

        Warehouse updatedWarehouse = warehouseRepository.save(existingWarehouse);

        return mapToWarehouseResponse(updatedWarehouse);
    }

    @Override
    public void deleteWarehouse(Long id) {
        // Implementation for deleting a warehouse
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with id: " + id));

        warehouseRepository.delete(warehouse);
    }

    private WarehouseResponse mapToWarehouseResponse(Warehouse warehouse) {
        return new WarehouseResponse(
                warehouse.getId(),
                warehouse.getCode(),
                warehouse.getName(),
                warehouse.getLocation(),
                warehouse.getCreatedAt(),
                warehouse.getUpdatedAt()
        );
    }

}
