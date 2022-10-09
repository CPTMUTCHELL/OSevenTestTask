package com.example.controller;

import com.example.dto.ProductDto;
import com.example.dto.ProviderDto;
import com.example.exception.CustomException;
import com.example.service.ProviderService;
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

@RestController
@Validated
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderService service;

    @GetMapping
    public ResponseEntity<List<ProviderDto>> getAll(
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

    @PutMapping("/activation/{id}")
    public ResponseEntity<ProviderDto> changeActivationById(@RequestBody boolean deactivated,
                                                         @PathVariable int id) throws CustomException {
        var updated = service.changeActivation(id, deactivated);
        var responseDto = ProviderDto.builder()
                .product(updated.getProducts().get(0).getProduct())
                .name(updated.getName())
                .price(updated.getProducts().get(0).getPrice())
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @GetMapping("/newProvider/{id}")
    public ResponseEntity<List<ProductDto>> getNewProviderById(
            @RequestHeader
            @Valid
            @Pattern(regexp = "^[a-zA-Z0-9_]{6}$", message = "Invalid token") String token,
            @PathVariable Integer id) throws CustomException {
        var response = service.getAllNewProvidersActiveByCampaign(id,token);
        var productsDto = new ArrayList<ProductDto>();
        response.getProducts()
                .forEach(el->{
                    var productDto = ProductDto.builder()
                                    .product(el.getProduct())
                                    .price(el.getPrice())
                                    .build();
                            productsDto.add(productDto);
                        });
        return new ResponseEntity<>(productsDto,HttpStatus.OK);
    }
}

