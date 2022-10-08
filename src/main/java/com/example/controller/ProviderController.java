package com.example.controller;

import com.example.dto.ProviderDto;
import com.example.entity.Campaign;
import com.example.entity.Provider;
import com.example.exception.CustomException;
import com.example.service.ProviderService;
import com.example.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/activation/{id}")
    public ResponseEntity<Provider> changeActivationById(@RequestBody boolean deactivated,
                                                         @PathVariable int id) throws CustomException {
        var providerToUpdate = service.getById(id);
        providerToUpdate.setDeactivated(deactivated);
        var saved = service.save(providerToUpdate);
        return new ResponseEntity<>(saved, HttpStatus.OK);

    }}
