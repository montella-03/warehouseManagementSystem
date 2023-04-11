package com.warehouse.warehouseManagementSystem.controller;

import com.warehouse.warehouseManagementSystem.model.ProductRequest;
import com.warehouse.warehouseManagementSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<Integer> receiveProduct
            (@RequestBody ProductRequest productRequest){
        Integer request = productService.addProduct(productRequest);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
}
