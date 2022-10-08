package com.example.controller;

import com.example.entity.Campaign;
import com.example.exception.CustomException;
import com.example.service.CampaignService;
import com.example.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
@RequiredArgsConstructor
public class CampaignController {
    private final CampaignService service;
    private final TokenService tokenService;

    @PutMapping("/activation/{id}")
    public ResponseEntity<Campaign> changeActivationById(@RequestBody boolean deactivated,
                                                   @PathVariable int id) throws CustomException {
        var campaignToUpdate = service.getById(id);
        campaignToUpdate.setDeactivated(deactivated);
        tokenService.changeActivationByCampaignId(id,deactivated);
        Campaign saved = service.save(campaignToUpdate);
        return new ResponseEntity<>(saved, HttpStatus.OK);

    }

}
