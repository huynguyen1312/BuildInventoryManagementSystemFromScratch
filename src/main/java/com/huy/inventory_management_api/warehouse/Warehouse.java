package com.huy.inventory_management_api.warehouse;

import jakarta.persistence.*;  
import lombok.*;  
  
import java.time.LocalDateTime;  
  
@Entity  
@Table(name = "warehouses")  
@Getter  
@Setter  
@NoArgsConstructor  
@AllArgsConstructor  
@Builder  
public class Warehouse {  
  
    @Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;  
  
    @Column(nullable = false, unique = true, length = 50)  
    private String code;  
  
    @Column(nullable = false, length = 150)  
    private String name;  
  
    private String location;  
  
    @Column(name = "created_at", nullable = false, updatable = false)  
    private LocalDateTime createdAt;  
  
    @Column(name = "updated_at", nullable = false)  
    private LocalDateTime updatedAt;  
  
    @PrePersist  
    public void prePersist() {  
        LocalDateTime now = LocalDateTime.now();  
        this.createdAt = now;  
        this.updatedAt = now;  
    }  
  
    @PreUpdate  
    public void preUpdate() {  
        this.updatedAt = LocalDateTime.now();  
    }  
}

