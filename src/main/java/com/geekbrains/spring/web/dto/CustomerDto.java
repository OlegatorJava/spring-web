package com.geekbrains.spring.web.dto;


public class CustomerDto {

    private Long id;

    private String name;


    public CustomerDto() {
    }

    public CustomerDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CustomerDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
