package com.example.controller;

import com.example.dto.ProductDto;
import com.example.dto.ProviderDto;
import com.example.entity.Campaign;
import com.example.exception.CustomException;
import com.example.exception.ErrorResponse;
import com.example.service.ProviderService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Api(tags = "Provider controller")

@RestController
@Validated
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderService service;

    @ApiOperation(value = "Get all campaign's provides")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok",response = ProviderDto.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Token not found",response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Provider not found",response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "Invalid token",response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal error")
    }
    )
    @GetMapping
    public ResponseEntity<List<ProviderDto>> getAll(
            @ApiParam(type = "header", value = "Campaign token" ,example = "123456",required = true)
            @RequestHeader
            @Valid
            @Pattern(regexp = "^[a-zA-Z0-9_]{6}$", message = "Invalid token") String token) throws CustomException {
        var providers = service.getAllActiveByCampaign(token);
        var response = providers.stream()
                .filter(el->el.getUrl()==null)
                .map(el -> ProviderDto.builder()
                        .name(el.getName())
                        .price(el.getProducts().get(0).getPrice())
                        .product(el.getProducts().get(0).getProduct())
                        .build())
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @ApiOperation(value = "Change activation status of a provide")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok",response = ProviderDto.class),
            @ApiResponse(code = 404, message = "Provider not found",response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal error")
    }
    )
    @PutMapping("/activation/{id}")
    public ResponseEntity<ProviderDto> changeActivationById(
            @ApiParam(name = "data", value = "activation status (boolean)", type = "boolean" ,example = "true",required = true)
            @RequestBody boolean deactivated,
            @ApiParam(name = "id", example = "1", type = "String", value = "provider id", required = true)
            @PathVariable int id) throws CustomException {
        var updated = service.changeActivation(id, deactivated);
        var responseDto = ProviderDto.builder()
                .product(updated.getProducts().get(0).getProduct())
                .name(updated.getName())
                .price(updated.getProducts().get(0).getPrice())
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @ApiOperation(value = "Get a campaign's new provider by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok",response = ProductDto[].class),
            @ApiResponse(code = 404, message = "Token not found",response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Provider not found",response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "Invalid token",response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal error")
    }
    )
    @GetMapping("/newProvider/{id}")
    public ResponseEntity<ProductDto[]> getNewProviderById(
            @ApiParam(type = "header", value = "Campaign token" ,example = "123456",required = true)
            @RequestHeader @Valid @Pattern(regexp = "^[a-zA-Z0-9_]{6}$", message = "Invalid token") String token,
            @ApiParam(name = "id", example = "3", type = "String", value = "provider id", required = true)
            @PathVariable Integer id) throws CustomException {
        var response = service.getAllNewProvidersActiveByCampaign(id,token);
//        var productsDto = new ArrayList<ProductDto>();
//        response.getProducts()
//                .forEach(el->{
//                    var productDto = ProductDto.builder()
//                                    .product(el.getProduct())
//                                    .price(el.getPrice())
//                                    .build();
//                            productsDto.add(productDto);
//                        });
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}

