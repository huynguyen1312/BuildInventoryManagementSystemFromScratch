package com.huy.inventory_management_api.stockmovement;

import jakarta.persistence.*;
import lombok.*;
import com.huy.inventory_management_api.product.Product;
import com.huy.inventory_management_api.warehouse.Warehouse;  
  
import java.time.LocalDateTime;  
  
@Entity  
@Table(name = "stock_movements")  
@Getter  
@Setter  
@NoArgsConstructor  
@AllArgsConstructor  
@Builder  

public class StockMovement {  
  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
  
    @ManyToOne(fetch = FetchType.LAZY, optional = false)  
    @JoinColumn(name = "product_id", nullable = false)  
    private Product product;  
  
    @ManyToOne(fetch = FetchType.LAZY, optional = false)  
    @JoinColumn(name = "warehouse_id", nullable = false)  
    private Warehouse warehouse;  
  
    @Enumerated(EnumType.STRING)  
    @Column(nullable = false, length = 20)  
    private MovementType type;  
  
    @Column(nullable = false)  
    private Integer quantity;  
  
    @Column(length = 255)
    private String note;  
  
    @Column(name = "created_at", nullable = false, updatable = false)  
    private LocalDateTime createdAt;  
  
    @PrePersist  
    public void prePersist() {  
        this.createdAt = LocalDateTime.now();  
    }  
}

