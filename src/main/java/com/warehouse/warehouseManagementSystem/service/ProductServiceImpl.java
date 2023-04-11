package com.warehouse.warehouseManagementSystem.service;

import com.warehouse.warehouseManagementSystem.Repository.ProductRepository;
import com.warehouse.warehouseManagementSystem.entity.Product;
import com.warehouse.warehouseManagementSystem.model.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;
import java.util.regex.Pattern;

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
