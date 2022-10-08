package com.example.service;

import com.example.entity.Campaign;
import com.example.entity.Provider;
import com.example.entity.Token;
import com.example.exception.CustomException;
import com.example.exception.ErrorResponse;
import com.example.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProviderService {
    private final TokenService tokenService;
    private final ProviderRepository repository;

    public List<Provider> getAllActiveByCampaign(String token) throws CustomException {
        Token tokenEntity = tokenService.validateByValue(token);
        if (tokenEntity!=null) {
            return repository.findAll().stream()
                    .filter(el-> !el.isDeactivated())
                    .collect(Collectors.toList());

        }
        else throw new CustomException("Not found", HttpStatus.NOT_FOUND, new ErrorResponse("Not Found"));

    }
}
