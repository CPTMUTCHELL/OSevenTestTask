package com.example;
import com.example.exception.CustomException;
import com.example.service.CampaignService;
import com.example.service.ProviderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// https://stackoverflow.com/questions/19813492/getting-lazyinitializationexception-on-junit-test-case
@Transactional
public class ProviderTests {
    @Autowired
    private ProviderService service;
    @Autowired
    private CampaignService campaignService;
    private final String token = "123456";

    @Test
    void getProvidersWhenTheCampaignIsActive() throws CustomException {
        var providers = service.getAllActiveByCampaign(token);
        assertEquals(2, providers.size());
    }

    @Test
    void getProvidersWhenTheCampaignIsDeactivated() throws CustomException {
        var campaign = campaignService.getById(1);
        campaign.setDeactivated(true);
        campaignService.save(campaign);
        var msg = "Token belongs to deactivated campaign";
        var ex = assertThrows(CustomException.class, () -> service.getAllActiveByCampaign(token));
        assertEquals(msg, ex.getMessage());
    }

    @Test
    void getUnExistingProviderThrowsException() {
        var msg = "Provider not found";
        var ex = assertThrows(CustomException.class, () -> service.getById(999));
        assertEquals(msg, ex.getMessage());
    }
    @Test
    void deactivateProvider() throws CustomException {
        var provider = service.getById(1);
        provider.setDeactivated(true);
        service.save(provider);
        provider=service.getById(1);
        assertTrue(provider.isDeactivated());
    }
    @Test
    void noProvidersShouldReturnWhenDeactivated() throws CustomException {
        service.changeActivation(1,true);
        service.changeActivation(2,true);
        var providers =service.getAllActiveByCampaign(token);
        assertEquals(0, providers.size());
    }

}
