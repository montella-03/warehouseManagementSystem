package com.warehouse.warehouseManagementSystem.controller;

import com.warehouse.warehouseManagementSystem.model.ProductRequest;
import com.warehouse.warehouseManagementSystem.model.ProductResponse;
import com.warehouse.warehouseManagementSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Integer> receiveProduct
            (@RequestBody ProductRequest productRequest) {
        Integer request = productService.addProduct(productRequest);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){
        List<ProductResponse> productResponseList =
                productService.getAll();
        return new ResponseEntity<>(productResponseList,HttpStatus.OK);
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductRequest> updateProduct
            (@RequestBody ProductRequest productRequest, @PathVariable Integer id){
       ProductRequest productRequest1 = productService.updateProduct(productRequest,id);
        return new ResponseEntity<>(productRequest1,HttpStatus.OK);
    }

}
