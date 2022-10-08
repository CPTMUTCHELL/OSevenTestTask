package com.example.service;

import com.example.entity.Token;
import com.example.exception.CustomException;
import com.example.exception.ErrorResponse;
import com.example.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenRepository repository;
    public boolean validateByValue(String value) throws CustomException {
        var token = repository.findByValue(value)
                .orElseThrow(()->new CustomException("Token not found", HttpStatus.NOT_FOUND, new ErrorResponse("Token not Found")));
        if (token.getCampaign().isDeactivated())
            throw new CustomException("Token belongs to deactivated campaign", HttpStatus.NOT_FOUND, new ErrorResponse("Token belongs to deactivated campaign"));
        return true;
    }
    public List<Token> getAll(){
        return repository.findAll();
    }
    public void changeActivationByCampaignId(Integer id,boolean activation){
        for (Token token:getAll()){
            if (token.getCampaign().getId().equals(id)){
                token.setDeactivated(activation);
                repository.save(token);
            }
        }
    }
}

