package com.geekbrains.spring.web.validators;

import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.exceptions.ValidationExceptions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {

    public void validate(ProductDto productDto){
        List<String> errors = new ArrayList<>();
        if(productDto.getCost() < 1){
            errors.add("Цена продукта не может быть меньше 1");
        }
        if(productDto.getTitle().isBlank() || productDto.getTitle().isEmpty()){
            errors.add("Название продукта не может быть пустым");
        }
        if(!errors.isEmpty()){
            throw new ValidationExceptions(errors);
        }
    }

}
