package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.converters.ProductMapper;
import com.geekbrains.spring.web.data.Customer;
import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.dto.CustomerDto;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundExceptions;
import com.geekbrains.spring.web.services.ProductService;
import com.geekbrains.spring.web.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final ProductValidator productValidator;


    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_cost", required = false) Integer minCost,
            @RequestParam(name = "max_cost", required = false) Integer maxCost,
            @RequestParam(name = "name", required = false) String name
    ) {
        if(page <=0){
            page = 1;
        }

        return productService.find(minCost,maxCost,name,page).map(
                ProductMapper.MAPPER::fromProduct);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundExceptions("Невозможно показать продукт с id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }

    @PostMapping
    public void createProduct(@RequestBody ProductDto productDto){
        productValidator.validate(productDto);
        productDto.setId(null);
        productService.createProduct(productDto);
    }


    @PutMapping
    public void updateProduct(@RequestBody ProductDto productDto){
        productValidator.validate(productDto);
        productService.updateProduct(productDto);
    }




}
