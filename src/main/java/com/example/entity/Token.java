package com.example.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "token")
@ApiModel(description = "Token entity", value = "Token")

public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Token id ", example = "1")
    private Integer id;
    @Column(name = "token_value")
    @Pattern(regexp = "^[a-zA-Z0-9_]{6}$" ,message = "Token must be 6 char long alphanumeric")
    @ApiModelProperty(value = "Token value", example = "123456")
    private String value;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="campaign_id")
    @ApiModelProperty(value = "Campaign of the current token")
    private Campaign campaign;
    @ApiModelProperty(value = "Token activation status", example = "false")
    private boolean deactivated;

}
