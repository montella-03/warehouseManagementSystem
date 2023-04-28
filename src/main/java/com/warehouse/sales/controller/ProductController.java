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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Long> receiveProduct
            (@RequestBody ProductRequest productRequest) {
        Long request = productService.addProduct(productRequest);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(){
        List<ProductResponse> productResponseList =
                productService.getAll();
        return new ResponseEntity<>(productResponseList,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductsById(@PathVariable Long id){
        ProductResponse product =
                productService.getById(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct
            (@RequestBody ProductRequest productRequest, @PathVariable Long id){
       String productRequest1 = productService.updateProduct(productRequest,id);
        return new ResponseEntity<>(productRequest1,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteProduct(@PathVariable Long id){
        Long product = productService.getDeleteById(id);
        return  new ResponseEntity<>(product,HttpStatus.OK);
    }
    @PutMapping("/reduce/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable Long id,
                                               @RequestParam Long quantity){
        productService.reduceQuantity(id,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/increase/{id}")
    public ResponseEntity<Void> increaseQuantity(@PathVariable Long id,
                                               @RequestParam Long quantity){
         productService.increseQuantity(id,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
