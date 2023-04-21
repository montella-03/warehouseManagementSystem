package com.warehouse.sales.service;

import com.warehouse.sales.Repository.ProductRepository;
import com.warehouse.sales.entity.Product;
import com.warehouse.sales.model.ProductRequest;
import com.warehouse.sales.model.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Long addProduct(ProductRequest productRequest) {
        String regex = "^\\d{3}[a-zA-Z]{3}\\d{3}$";
        Pattern pattern = Pattern.compile(regex);

        String code = generateRandomCode(pattern);
        Product product = Product.builder()
                .productName(productRequest.product())
                .quantity(productRequest.quantity())
                .price(productRequest.price())
                .referenceCode(code)
                .status("available")
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
                        (product.getProductName(),product.getQuantity(),
                                product.getPrice(),
                                product.getStatus(),product.getId(),
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
    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                ()->new RuntimeException("message"));
        return new ProductResponse(product.getProductName(),product.getQuantity(),
                product.getPrice(),product.getStatus(),
                product.getId(),product.getReferenceCode());
    }

    @Override
    public Long getDeleteById(Long id) {
        Product product = productRepository.findById(id).orElseThrow
                (()->new RuntimeException("product not found"));
        productRepository.delete(product);
        return product.getId();
    }

    @Override
    public String updateProduct(ProductRequest productRequest, Long id) {
        Product product = productRepository.findById(id).orElseThrow
                (()->new RuntimeException("product modified is not available"));
        if(productRequest.product()!=null){
            product.setProductName(productRequest.product());
        }
        if(productRequest.quantity()!=null){
            product.setQuantity(productRequest.quantity());
        }
        if(productRequest.price()!=null){
            product.setPrice(productRequest.price());
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
                builder.append(random.nextInt(4));
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
