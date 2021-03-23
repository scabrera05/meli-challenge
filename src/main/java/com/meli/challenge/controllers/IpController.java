package com.meli.challenge.controllers;

import com.meli.challenge.domain.dto.TraceResponseDto;
import com.meli.challenge.domain.model.IpRequestModel;
import com.meli.challenge.service.TraceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/challenge")
public class IpController {

    private final TraceService traceService;

    public IpController(TraceService traceService) {
        this.traceService = traceService;
    }

    @GetMapping("/ping")
    @ResponseStatus(HttpStatus.OK)
    public void ping() {
        // Returns OK if IP Trace service is up
    }

    @PostMapping(value = "/trace")
    public TraceResponseDto trace(@RequestBody(required = true) IpRequestModel ipModel) {

        // TODO: Agregar interfaz de TraceService
        TraceResponseDto response = traceService.traceIp(ipModel.getIp());

        return response;

    }

}