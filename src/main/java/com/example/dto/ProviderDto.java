package com.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class ProviderDto {
    private String name;
    private double price;
    private String product;
}
