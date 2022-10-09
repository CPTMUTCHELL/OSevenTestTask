package com.example.controller;

import com.example.entity.Campaign;
import com.example.exception.CustomException;
import com.example.exception.ErrorResponse;
import com.example.service.CampaignService;
import com.example.service.TokenService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "Campaign controller")

@RestController
@RequestMapping("/campaigns")
@RequiredArgsConstructor
public class CampaignController {
    private final CampaignService service;

    @ApiOperation(value = "Change activation status of a campaign")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok",response = Campaign.class),
            @ApiResponse(code = 404, message = "Campaign not found",response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal error")
    }
    )
    @PutMapping("/activation/{id}")
    public ResponseEntity<Campaign> changeActivationById(
            @ApiParam(name = "data", value = "activation status (boolean)", type = "boolean" ,example = "true",required = true)
            @RequestBody boolean deactivated,
            @ApiParam(name = "id", example = "1", type = "String", value = "campaign id", required = true)
            @PathVariable int id) {

        var updatedCampaign = service.changeActivation(id,deactivated);
        return new ResponseEntity<>(updatedCampaign, HttpStatus.OK);

    }

}
