package com.example.emailingservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sms")
@RequiredArgsConstructor
public class Controller {
    private final Service service;
    @PostMapping
    public void sendSms(@RequestBody SmsRequest smsRequest) {
        service.sendSms(smsRequest);
    }

}
