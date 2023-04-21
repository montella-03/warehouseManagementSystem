package com.warehouse.sales.controller;

import com.warehouse.sales.model.ProductRequest;
import com.warehouse.sales.model.ProductResponse;
import com.warehouse.sales.service.ProductService;
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

    @PostMapping
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
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductsById(@PathVariable int id){
        ProductResponse product =
                productService.getById(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct
            (@RequestBody ProductRequest productRequest, @PathVariable Integer id){
       String productRequest1 = productService.updateProduct(productRequest,id);
        return new ResponseEntity<>(productRequest1,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteProduct(@PathVariable int id){
        Integer product = productService.getDeleteById(id);
        return  new ResponseEntity<>(product,HttpStatus.OK);
    }

}
