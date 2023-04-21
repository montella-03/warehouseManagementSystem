package com.warehouse.sales.service;

import com.warehouse.sales.model.ProductRequest;
import com.warehouse.sales.model.ProductResponse;

import java.util.List;

public interface ProductService {
    Integer addProduct(ProductRequest productRequest);

    List<ProductResponse> getAll();

    ProductResponse getById(Integer id);

    Integer getDeleteById(Integer id);

    String updateProduct(ProductRequest productRequest, Integer id);

}
