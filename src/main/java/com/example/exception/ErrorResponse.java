package com.example.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Exception message")
public class ErrorResponse {
    @ApiModelProperty(value = "Exception text", example = "Exception occurred", required = true)

    private String message;
}
