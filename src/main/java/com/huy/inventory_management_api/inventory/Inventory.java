package com.huy.inventory_management_api.inventory;

import com.huy.inventory_management_api.product.Product;  
import com.huy.inventory_management_api.warehouse.Warehouse;  
import jakarta.persistence.*;  
import lombok.*;  
  
import java.time.LocalDateTime;  
  
@Entity  
@Table(  
    name = "inventories",  
    uniqueConstraints = {  
        @UniqueConstraint(  
            name = "uk_inventory_product_warehouse",  
            columnNames = {"product_id", "warehouse_id"}  
        )  
    }  
)  
@Getter  
@Setter  
@NoArgsConstructor  
@AllArgsConstructor  
@Builder  
public class Inventory {  
  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
  
    @ManyToOne(fetch = FetchType.LAZY, optional = false)  
    @JoinColumn(name = "product_id", nullable = false)  
    private Product product;  
  
    @ManyToOne(fetch = FetchType.LAZY, optional = false)  
    @JoinColumn(name = "warehouse_id", nullable = false)  
    private Warehouse warehouse;  
  
    @Column(nullable = false)  
    private Integer quantity;  
  
    @Column(name = "updated_at", nullable = false)  
    private LocalDateTime updatedAt;  
  
    @PrePersist  
    @PreUpdate  
    public void updateTimestamp() {  
        this.updatedAt = LocalDateTime.now();  
    }  
}