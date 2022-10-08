package com.example.controller;

import com.example.dto.ProviderDto;
import com.example.entity.Provider;
import com.example.exception.CustomException;
import com.example.service.ProviderService;
import com.example.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderService service;
    @GetMapping
    public ResponseEntity <List<ProviderDto>> getAll(
            @RequestHeader
            @Valid
            @Pattern(regexp = "^[a-zA-Z0-9_]{6}$" ,message = "Invalid token")  String token) throws CustomException {
        var providers = service.getAllActiveByCampaign(token);
        var response = providers.stream()
                .map(el->ProviderDto.builder()
                        .product(el.getProduct())
                        .price(el.getPrice())
                        .name(el.getName())
                        .build())
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
