package com.example.dto;

import lombok.Builder;


@Builder
public class ProviderDto {
    private String name;
    private double price;
    private String product;
    private boolean deactivated;
}
