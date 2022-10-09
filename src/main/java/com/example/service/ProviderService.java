package com.example.service;

import com.example.entity.Campaign;
import com.example.entity.Provider;
import com.example.entity.Token;
import com.example.exception.CustomException;
import com.example.exception.ErrorResponse;
import com.example.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProviderService {
    private final TokenService tokenService;
    private final ProviderRepository repository;

    public List<Provider> getAllActiveByCampaign(String token) throws CustomException {
        tokenService.validateByValue(token);
            return repository.findAll().stream()
                    .filter(el-> ! el.isDeactivated())
                    .collect(Collectors.toList());



    }
    public Provider getById(Integer id) throws CustomException {
        var msg = "Provider not found";
        return repository.findById(id)
                .orElseThrow(()->new CustomException(msg, HttpStatus.NOT_FOUND, new ErrorResponse(msg)));
    }
    public Provider save(Provider provider){
        return repository.save(provider);
    }
    public Provider changeActivation(Integer id, boolean deactivated) throws CustomException {
        var providerToUpdate = getById(id);
        providerToUpdate.setDeactivated(deactivated);
        return save(providerToUpdate);
    }
    public Provider getAllNewProvidersActiveByCampaign (Integer providerId, String token) throws CustomException {
        tokenService.validateByValue(token);
        var provider = getById(providerId);
        var template = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(2))
                .build();
       return template.getForEntity(provider.getUrl(),Provider.class).getBody();

    }
}
