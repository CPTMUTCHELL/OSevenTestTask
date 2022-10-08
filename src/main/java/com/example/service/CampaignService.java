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
    private final TokenService tokenService;

    public Campaign getById(Integer id) throws CustomException {
        var msg = "Campaign not found";

        return repository.findById(id)
                .orElseThrow(()->new CustomException(msg, HttpStatus.NOT_FOUND, new ErrorResponse(msg)));
    }
    public Campaign save(Campaign c){
        return repository.save(c);
    }
    public Campaign changeActivation(Integer id, boolean deactivated){
        var campaignToUpdate = repository.getById(id);
        campaignToUpdate.setDeactivated(deactivated);
        tokenService.changeActivationByCampaignId(id,deactivated);
        return save(campaignToUpdate);
    }

}
