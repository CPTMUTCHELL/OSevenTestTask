package com.example;

import com.example.entity.Token;
import com.example.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class TokenTests {
    @Autowired
    private TokenService service;
    @Test
    void allTestsShouldBeDeactivated(){
        service.changeActivationByCampaignId(1,true);
        var tokens = service.getAll();
        assertTrue(tokens.stream().allMatch(Token::isDeactivated));

    }
}
