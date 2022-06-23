package com.riu.payment.controller;

import com.riu.payment.model.OauthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/oauth")
@Component
public class OauthController {

    @GetMapping("/userinfo")
    public OauthResponse getUserInfo() {
        return new OauthResponse();
    }
}
