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
@CrossOrigin(origins = "http://localhost:5173/")
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
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponse> getProductsById(@PathVariable int id){
        ProductResponse product =
                productService.getById(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct
            (@RequestBody ProductRequest productRequest, @PathVariable Integer id){
       String productRequest1 = productService.updateProduct(productRequest,id);
        return new ResponseEntity<>(productRequest1,HttpStatus.OK);
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Integer> deleteProduct(@PathVariable int id){
        Integer product = productService.getDeleteById(id);
        return  new ResponseEntity<>(product,HttpStatus.OK);
    }

}
