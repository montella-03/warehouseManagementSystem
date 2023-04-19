package com.warehouse.warehouseManagementSystem.service;

import com.warehouse.warehouseManagementSystem.Repository.ProductRepository;
import com.warehouse.warehouseManagementSystem.entity.Product;
import com.warehouse.warehouseManagementSystem.model.ProductRequest;
import com.warehouse.warehouseManagementSystem.model.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Integer addProduct(ProductRequest productRequest) {
        String regex = "^\\d{3}[a-zA-Z]{3}\\d{3}$";
        Pattern pattern = Pattern.compile(regex);

        String code = generateRandomCode(pattern);
        Product product = Product.builder()
                .productName(productRequest.product())
                .grade(productRequest.quality())
                .quantity(productRequest.quantity())
                .handler(productRequest.handler())
                .referenceCode(code)
                .period(Instant.now())
                .build();
        productRepository.save(product);
        return product.getId();
    }


    @Override
    public List<ProductResponse> getAll() {
        List<Product> productList =
                productRepository.findAll();

        return productList.stream()
                .map(product -> new ProductResponse
                        (product.getProductName(),product.getQuantity(),product.getGrade(),
                                product.getHandler(),product.getPeriod(),product.getId(),
                                product.getReferenceCode()))
                .collect(Collectors.toList());
    }

//    @Override
//    public Integer deleteProductById(Integer id) {
//        Product product = productRepository.findById(id)
//                .orElseThrow(()->new RuntimeException("error"));
//        productRepository.delete(product);
//        return product.getId();
//    }

    @Override
    public ProductResponse getById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(
                ()->new RuntimeException("message"));
        return new ProductResponse(product.getProductName(),product.getQuantity(),
                product.getGrade(),product.getHandler(),product.getPeriod(),
                product.getId(),product.getReferenceCode());
    }

    @Override
    public Integer getDeleteById(Integer id) {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
        return product.getId();
    }

    @Override
    public String updateProduct(ProductRequest productRequest, Integer id) {
        Product product = productRepository.findById(id).orElseThrow
                (()->new RuntimeException("product modified is not available"));
        if(productRequest.product()!=null){
            product.setProductName(productRequest.product());
        }
        if(productRequest.quantity()!=null){
            product.setQuantity(productRequest.quantity());
        }
        if(productRequest.quality()!=null){
            product.setGrade(productRequest.quality());
        }
        if(productRequest.handler()!=null){
            product.setHandler(productRequest.handler());
        }
        productRepository.save(product);
        return product.getQuantity();
    }


    private String generateRandomCode(Pattern pattern) {
        Random random = new Random();
        String code;
        do {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                builder.append(random.nextInt(10));
            }
            for (int i = 0; i < 3; i++) {
                int ascii = random.nextInt(17) + (random.nextBoolean() ? 33 : 58);
                builder.append((char) ascii);
            }
            for (int i = 0; i < 3; i++) {
                builder.append(random.nextInt(10));
            }
            code = builder.toString();
        } while (!pattern.matcher(code).matches());
        return code;
    }
}
