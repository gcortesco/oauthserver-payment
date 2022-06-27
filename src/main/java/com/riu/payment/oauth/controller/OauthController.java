package com.riu.payment.oauth.controller;

import com.riu.payment.model.OauthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@RestController
@Component
public class OauthController {
    @Value("${user.oauth.clientId}")
    private String clientId;

    @GetMapping("/oauth/userinfo")
    public OauthResponse getUserInfo() {
        OauthResponse oauthResponse = new OauthResponse();
        oauthResponse.setSub(clientId);
       return oauthResponse;
    }






}
