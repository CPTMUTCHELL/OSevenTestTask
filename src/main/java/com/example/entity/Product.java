package com.example.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "product")
@Entity
@ApiModel(description = "Product entity", value = "Product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Product id ", example = "2")
    private Integer id;
    @ApiModelProperty(value = "Product name", example = "milk")
    private String product;
    @ApiModelProperty(value = "Product price", example = "4.2")
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="provider_id")
    @ApiModelProperty(value = "Provider of the current product")
    private Provider provider;
}
