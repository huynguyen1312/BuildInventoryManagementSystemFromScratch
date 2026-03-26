package com.huy.inventory_management_api.stockmovement;

import com.huy.inventory_management_api.product.Product;
import com.huy.inventory_management_api.product.ProductRepository;
import com.huy.inventory_management_api.stockmovement.DTO.StockMovementResponse;
import com.huy.inventory_management_api.warehouse.Warehouse;
import com.huy.inventory_management_api.warehouse.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StockMovementServiceImpl implements StockMovementService {
    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public StockMovementServiceImpl(StockMovementRepository stockMovementRepository,
                                    ProductRepository productRepository,
                                    WarehouseRepository warehouseRepository) {
        this.stockMovementRepository = stockMovementRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<StockMovementResponse> getAllStockMovements() {
        List<StockMovement> stockMovements = stockMovementRepository.findAll();

        return stockMovements.stream()
                .map(this::mapToStockMovementResponse)
                .toList();
    }

    @Override
    public StockMovementResponse getStockMovementById(Long id) {
        StockMovement stockMovement = stockMovementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock movement not found with id: " + id));

        return mapToStockMovementResponse(stockMovement);
    }

    @Override
    public List<StockMovementResponse> getStockMovementsByProductId(Long productId) {
        return stockMovementRepository.findByProductId(productId)
                .stream()
                .map(this::mapToStockMovementResponse)
                .toList();
    }

    @Override
    public List<StockMovementResponse> getStockMovementsByWarehouseId(Long warehouseId) {
        return stockMovementRepository.findByWarehouseId(warehouseId)
                .stream()
                .map(this::mapToStockMovementResponse)
                .toList();
    }

    @Override
    public List<StockMovementResponse> getStockMovementsByProductIdAndWarehouseId(Long productId, Long warehouseId) {
        return stockMovementRepository.findByProductIdAndWarehouseId(productId, warehouseId)
                .stream()
                .map(this::mapToStockMovementResponse)
                .toList();
    }
    
    private StockMovementResponse mapToStockMovementResponse(StockMovement stockMovement) {
        return new StockMovementResponse(
                stockMovement.getId(),
                stockMovement.getProduct().getId(),
                stockMovement.getProduct().getSku(),
                stockMovement.getProduct().getName(),
                stockMovement.getWarehouse().getId(),
                stockMovement.getWarehouse().getCode(),
                stockMovement.getWarehouse().getName(),
                stockMovement.getType(),
                stockMovement.getQuantity(),
                stockMovement.getNote(),
                stockMovement.getCreatedAt()
        );
    }
}
