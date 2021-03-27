package com.meli.challenge.controllers;

import com.meli.challenge.domain.dto.StatsResponseDto;
import com.meli.challenge.domain.dto.TraceResponseDto;
import com.meli.challenge.domain.model.IpRequestModel;
import com.meli.challenge.service.TraceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity ping() {
        // Returns OK if IP Trace service is up
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/trace")
    public ResponseEntity<TraceResponseDto> trace(@RequestBody(required = true) IpRequestModel ipModel) {

        TraceResponseDto traceInformation = traceService.traceIp(ipModel.getIp());

        if (traceInformation == null) {
            // Información de IP no encontrada
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(traceInformation, HttpStatus.OK);

    }

    @GetMapping(value = "/stats")
    public ResponseEntity<StatsResponseDto> getStatistics() {

        StatsResponseDto statistics = traceService.getStatistics();

        return new ResponseEntity(statistics, HttpStatus.OK);

    }

}
