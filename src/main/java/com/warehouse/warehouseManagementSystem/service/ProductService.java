package com.warehouse.warehouseManagementSystem.service;

import com.warehouse.warehouseManagementSystem.model.ProductRequest;
import com.warehouse.warehouseManagementSystem.model.ProductResponse;

import java.util.List;

public interface ProductService {
    Integer addProduct(ProductRequest productRequest);

    List<ProductResponse> getAll();
}
