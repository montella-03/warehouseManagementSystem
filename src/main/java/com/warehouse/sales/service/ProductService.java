package com.warehouse.sales.service;

import com.warehouse.sales.model.ProductRequest;
import com.warehouse.sales.model.ProductResponse;

import java.util.List;

public interface ProductService {
    Long addProduct(ProductRequest productRequest);

    List<ProductResponse> getAll();

    ProductResponse getById(Long id);

    Long getDeleteById(Long id);

    String updateProduct(ProductRequest productRequest, Long id);

    void reduceQuantity(Long id, Long quantity);

    void increseQuantity(Long id, Long quantity);
}
