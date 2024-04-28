package com.logifuture.cloudGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBack() {
        return "User service is down";
    }


    @GetMapping("/walletServiceFallBack")
    public String walletServiceFallBack() {
        return "Wallet service is down";
    }


    @GetMapping("/paymentServiceFallBack")
    public String paymentServiceFallBack() {
        return "Payment service is down";
    }


}
