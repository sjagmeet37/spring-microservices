package com.microservice.productservice.repository;

import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductRequest productDto) {

        Product product = Product.builder()
                            .name(productDto.getName())
                            .description(productDto.getDescription())
                             .price(productDto.getPrice()).build();

        productRepository.save(product);
        log.info("Product saved " + product.getId());
    }





}
