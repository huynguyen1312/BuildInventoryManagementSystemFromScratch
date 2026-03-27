package com.huy.inventory_management_api.product;

import com.huy.inventory_management_api.common.exception.DuplicateResourceException;
import com.huy.inventory_management_api.common.exception.ResourceNotFoundException;
import com.huy.inventory_management_api.product.DTO.ProductRequest;
import com.huy.inventory_management_api.product.DTO.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        // Implementation for creating a product
        if(productRepository.existsBySku(request.getSku())) {
            throw new DuplicateResourceException("SKU already exists: " + request.getSku());
        }

        Product product = Product.builder()
                .sku(request.getSku())
                .name(request.getName())
                .description(request.getDescription())
                .unit(request.getUnit())
                .build();

        Product savedProduct = productRepository.save(product);

        return mapToProductResponse(savedProduct);
    }

    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getSku(),
                product.getUnit(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        // Implementation for getting all products

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        // Implementation for getting a product by ID
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        return mapToProductResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        // Implementation for updating a product
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        if (!product.getSku().equals(request.getSku())
            && productRepository.existsBySku(request.getSku())) {
        throw new DuplicateResourceException("SKU already exists: " + request.getSku());
    }
        // Update the product fields
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setUnit(request.getUnit());

        Product updatedProduct = productRepository.save(product);
        return mapToProductResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        // Implementation for deleting a product
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        productRepository.delete(existingProduct);
    }
}
