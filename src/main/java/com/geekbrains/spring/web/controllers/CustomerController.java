package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.data.Customer;
import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.services.CustomerService;
import com.geekbrains.spring.web.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customers/find/{id}")
    public Customer findById(@PathVariable Long id) {
        return customerService.findById(id);
    }
    @GetMapping("/customers/delete/{id}")
    public void deleteById(@PathVariable Long id){
        customerService.deleteById(id);
    }
   /* @GetMapping("/customers/find_products/{id}")
    public List<ProductDto> findProductsById(Long id){
        return customerService.findProductsById(id);
    }*/


}
