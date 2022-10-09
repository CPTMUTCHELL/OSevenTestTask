package com.example.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
@ApiModel(description = "Product information", value = "Product")

public class ProductDto {
    @ApiModelProperty(value = "Product name", example = "milk")
    private String product;
    @ApiModelProperty(value = "Product price", example = "4.2")
    private double price;
}
