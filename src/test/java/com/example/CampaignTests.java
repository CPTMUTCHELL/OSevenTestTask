package com.example;

import com.example.entity.Campaign;
import com.example.exception.CustomException;
import com.example.service.CampaignService;
import com.example.service.TokenService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CampaignTests {
	@Autowired
	private CampaignService campaignService;

	@Test
	void getUnExistingCampaignThrowsException() {
		var msg = "Campaign not found";
		var ex = assertThrows(CustomException.class, () -> campaignService.getById(999));
		assertEquals(msg, ex.getMessage());
	}

	@Test
	void deactivateCampaign() {
		Campaign updated = campaignService.changeActivation(1, true);
		assertTrue(updated.isDeactivated());
	}

}
