package com.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@ApiModel(description = "Provider information", value = "Provider")
public class ProviderDto {
    @ApiModelProperty(value = "Provider name", example = "provider1")
    private String name;
    @ApiModelProperty(value = "Product price", example = "4.2")
    private double price;
    @ApiModelProperty(value = "Product name", example = "milk")
    private String product;
}
