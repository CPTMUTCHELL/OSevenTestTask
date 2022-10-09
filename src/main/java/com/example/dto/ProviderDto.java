package com.example.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProviderDto {
    private String name;
    private double price;
    private String product;
}
