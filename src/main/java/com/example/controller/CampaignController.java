package com.example.controller;

import com.example.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campaign")
@RequiredArgsConstructor
public class CampaignController {
    private final CampaignService service;


}
