package com.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class ProductDto {
    private String product;
    private double price;
}
