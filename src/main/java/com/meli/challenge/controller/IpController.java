package com.meli.challenge.controller;

import com.meli.challenge.domain.dto.TraceResponseDto;
import com.meli.challenge.domain.model.IpRequestModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/challenge")
public class IpController {

    @GetMapping("/ping")
    @ResponseStatus(HttpStatus.OK)
    public void ping() {
        // Returns OK if IP Trace service is up
    }

    @PostMapping(value = "/trace")
    public TraceResponseDto trace(@RequestBody(required = true) IpRequestModel ip) {

        TraceResponseDto response = new TraceResponseDto();
        response.setIp(ip.getIp());
        response.setCountry("Uruguay");
        response.setDate(new Date());
        response.setIsoCode("ES");

        return response;

    }

}
