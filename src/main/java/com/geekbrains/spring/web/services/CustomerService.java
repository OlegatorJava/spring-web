package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.configurations.JwtProperties;
import com.geekbrains.spring.web.data.Customer;
import com.geekbrains.spring.web.data.Product;
import com.geekbrains.spring.web.dto.CustomerDto;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.repositories.CustomerRepository;
import com.geekbrains.spring.web.repositories.ProductRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NaturalIdCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final JwtProperties properties;


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer findById(Long id) {
       return customerRepository.findById(id).orElseThrow();
    }

    public String getUsername(String bearerTokenValue) {
        return parse(bearerTokenValue).getSubject();
    }

    private Claims parse(String value){
        return Jwts.parser()
                .setSigningKey(properties.getSecret())
                .parseClaimsJws(value)
                .getBody();
    }

    public List<GrantedAuthority> getAuthority(String bearerTokenValue) {
        List<String> authority = (List<String>) parse(bearerTokenValue).get("authority");

        return authority.stream()
                .map(SimpleGrantedAuthority::new)
                .map(it -> (GrantedAuthority) it)
                .collect(Collectors.toList());
    }

    public String generateJwtToken(UserDetails user) {
        String username = user.getUsername();
        List<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Map<String, Object> claims = new HashMap<>(Map.of("authority", authorities));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + properties.getExpireTime()))
                .signWith(SignatureAlgorithm.HS256, properties.getSecret())
                .compact();
    }

    /*public List<ProductDto> findProductsById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        List<ProductDto> productDtos = customer.getProducts().stream().map(p -> new ProductDto(p.getId(),p.getTitle(),p.getCost()))
                .collect(Collectors.toList());
        return productDtos;
    }*/



}
