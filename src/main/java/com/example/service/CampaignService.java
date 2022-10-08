package com.example.service;

import com.example.entity.Campaign;
import com.example.exception.CustomException;
import com.example.exception.ErrorResponse;
import com.example.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CampaignService {
    private final CampaignRepository repository;

    public Campaign getById(Integer id) throws CustomException {
        var msg = "Campaign not found";

        return repository.findById(id)
                .orElseThrow(()->new CustomException(msg, HttpStatus.NOT_FOUND, new ErrorResponse(msg)));
    }
    public Campaign save(Campaign c){
        return repository.save(c);
    }

}
