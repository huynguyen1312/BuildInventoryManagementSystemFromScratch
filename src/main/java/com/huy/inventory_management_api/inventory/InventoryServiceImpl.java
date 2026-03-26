package com.huy.inventory_management_api.inventory;

import com.huy.inventory_management_api.inventory.DTO.InventoryResponse;
import com.huy.inventory_management_api.inventory.DTO.StockInRequest;
import com.huy.inventory_management_api.inventory.DTO.StockOutRequest;
import com.huy.inventory_management_api.product.Product;
import com.huy.inventory_management_api.product.ProductRepository;
import com.huy.inventory_management_api.stockmovement.MovementType;
import com.huy.inventory_management_api.stockmovement.StockMovement;
import com.huy.inventory_management_api.stockmovement.StockMovementRepository;
import com.huy.inventory_management_api.warehouse.Warehouse;
import com.huy.inventory_management_api.warehouse.WarehouseRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor

public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final StockMovementRepository stockMovementRepository;

    @Override
    @Transactional
    public InventoryResponse stockIn(StockInRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + request.getProductId()));

        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + request.getWarehouseId()));

        Inventory inventory = inventoryRepository
                .findByProductIdAndWarehouseId(product.getId(), warehouse.getId())
                .orElse(
                        Inventory.builder()
                                .product(product)
                                .warehouse(warehouse)
                                .quantity(0)
                                .build()
                );

        inventory.setQuantity(inventory.getQuantity() + request.getQuantity());

        Inventory savedInventory = inventoryRepository.save(inventory);

        StockMovement stockMovement = StockMovement.builder()
                .product(product)
                .warehouse(warehouse)
                .type(MovementType.IN)
                .quantity(request.getQuantity())
                .note(request.getNote())
                .build();

        stockMovementRepository.save(stockMovement);

        return mapToInventoryResponse(savedInventory);
    }

    @Override
    @Transactional
    public InventoryResponse stockOut(StockOutRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + request.getProductId()));

        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + request.getWarehouseId()));

        Inventory inventory = inventoryRepository
                .findByProductIdAndWarehouseId(product.getId(), warehouse.getId())
                .orElseThrow(() -> new RuntimeException(
                        "Inventory not found for product id " + product.getId()
                                + " and warehouse id " + warehouse.getId()
                ));

        if (inventory.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Insufficient stock. Current quantity: " + inventory.getQuantity());
        }

        inventory.setQuantity(inventory.getQuantity() - request.getQuantity());

        Inventory savedInventory = inventoryRepository.save(inventory);

        StockMovement stockMovement = StockMovement.builder()
                .product(product)
                .warehouse(warehouse)
                .type(MovementType.OUT)
                .quantity(request.getQuantity())
                .note(request.getNote())
                .build();

        stockMovementRepository.save(stockMovement);

        return mapToInventoryResponse(savedInventory);
    }

    @Override
    public List<InventoryResponse> getAllInventories() {
        return inventoryRepository.findAll()
                .stream()
                .map(this::mapToInventoryResponse)
                .toList();
    }

    @Override
    public InventoryResponse getInventoryById(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));

        return mapToInventoryResponse(inventory);
    }

    @Override
    public InventoryResponse getInventoryByProductAndWarehouse(Long productId, Long warehouseId) {
        Inventory inventory = inventoryRepository.findByProductIdAndWarehouseId(productId, warehouseId)
                .orElseThrow(() -> new RuntimeException(
                        "Inventory not found for product id " + productId + " and warehouse id " + warehouseId
                ));

        return mapToInventoryResponse(inventory);
    }

    @Override
    public List<InventoryResponse> getInventoriesByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId)
                .stream()
                .map(this::mapToInventoryResponse)
                .toList();
    }

    @Override
    public List<InventoryResponse> getInventoriesByWarehouseId(Long warehouseId) {
        return inventoryRepository.findByWarehouseId(warehouseId)
                .stream()
                .map(this::mapToInventoryResponse)
                .toList();
    }

    private InventoryResponse mapToInventoryResponse(Inventory inventory) {
        return new InventoryResponse(
                inventory.getId(),
                inventory.getProduct().getId(),
                inventory.getProduct().getSku(),
                inventory.getProduct().getName(),
                inventory.getWarehouse().getId(),
                inventory.getWarehouse().getCode(),
                inventory.getWarehouse().getName(),
                inventory.getQuantity(),
                inventory.getUpdatedAt()
        );
    }
}
