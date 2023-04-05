package com.geekbrains.spring.web.repositories.specification;

import com.geekbrains.spring.web.data.Product;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationProduct {

    public static Specification<Product> costGreaterOrEquals(Integer cost){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), cost));
    }

    public static Specification<Product> costLessOrEquals(Integer cost){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), cost));
    }

    public static Specification<Product> titleLike(String title){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", title)));
    }
}
