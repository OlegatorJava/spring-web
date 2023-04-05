package com.geekbrains.spring.web.repositories;

import com.geekbrains.spring.web.data.Customer;
import com.geekbrains.spring.web.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
