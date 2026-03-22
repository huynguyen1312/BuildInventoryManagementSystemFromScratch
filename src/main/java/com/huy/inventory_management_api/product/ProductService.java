package com.huy.inventory_management_api.product;

import com.huy.inventory_management_api.product.DTO.ProductRequest;
import com.huy.inventory_management_api.product.DTO.ProductResponse;
import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);
}
