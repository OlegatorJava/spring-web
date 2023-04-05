package com.geekbrains.spring.web.dto;

import com.geekbrains.spring.web.data.Product;
import lombok.Data;


import java.util.*;

@Data

public class Cart {
    private List<CartItem> cartItemList;
    private int totalPrice;

    public Cart() {
        this.cartItemList = new ArrayList<>();
    }

    public List<CartItem> getItems(){
        return Collections.unmodifiableList(cartItemList);
    }

    public void add(ProductDto product){

        for(CartItem item: cartItemList) {
            if (Objects.equals(item.getProductId(), product.getId())) {
                item.setQuantity();
                item.setPricePerProduct();
                return;
            }
        }

        cartItemList.add(new CartItem(product.getId(), product.getTitle(), 1, product.getCost(),product.getCost()));
        recalculate();

    }

    private void recalculate(){
        totalPrice = 0;
        for(CartItem item: cartItemList){
            totalPrice += item.getPrice();
        }
    }

    public void remove(Long id){
        cartItemList.removeIf(item -> item.getProductId().equals(id));
        recalculate();

    }
}
