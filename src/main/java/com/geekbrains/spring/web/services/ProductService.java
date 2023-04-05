package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.converters.ProductMapper;
import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.repositories.ProductRepository;
import com.geekbrains.spring.web.repositories.specification.SpecificationProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> find(Integer minCost, Integer maxCost, String name, Integer page){

        Specification<Product> spec = Specification.where(null);
        if(minCost != null){
            spec = spec.and(SpecificationProduct.costGreaterOrEquals(minCost));
        }
        if(maxCost != null){
            spec = spec.and(SpecificationProduct.costLessOrEquals(maxCost));
        }
        if(name != null){
            spec = spec.and(SpecificationProduct.titleLike(name));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 55));
    }

    public Optional<ProductDto> findById(Long id) {
       return Optional.of(productRepository.findById(id).map(ProductMapper.MAPPER::fromProduct).orElseThrow());
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    public void createProduct(ProductDto productDto){
        Product product = ProductMapper.MAPPER.toProduct(productDto);
        product.setDate(new Date(System.currentTimeMillis()));
        productRepository.save(product);
    }


    public void updateProduct(ProductDto productDto) {
        Product byId = productRepository.findById(productDto.getId()).orElseThrow();
        Product product = ProductMapper.MAPPER.toProduct(productDto);
        product.setDate(byId.getDate());
        productRepository.save(product);
    }

}
