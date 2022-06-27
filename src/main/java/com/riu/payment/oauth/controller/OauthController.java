package com.riu.payment.oauth.controller;


import com.riu.payment.model.OauthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Collections;


@RestController
@Component
public class OauthController {
    @Value("${user.oauth.clientId}")
    private String clientId;

    @GetMapping("/oauth/userinfo")
    public OauthResponse getUserInfo(HttpServletRequest request) throws Exception {
        OauthResponse oauthResponse = new OauthResponse();
        oauthResponse.setSub(clientId);
       return oauthResponse;
    }






}
