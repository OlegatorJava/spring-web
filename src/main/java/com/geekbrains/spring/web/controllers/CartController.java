package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.dto.Cart;
import com.geekbrains.spring.web.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartServise;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id){
        cartServise.add(id);
    }

    @GetMapping("/remove/{id}")
    public void removeProductFromCart(@PathVariable Long id){
        cartServise.remove(id);
    }

    @GetMapping
    public Cart getCurrentCart(){
        return cartServise.getCurrentCart();
    }
}
