package com.meli.challenge.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TraceResponseDto {

    private String ip;
    private Date date;
    private String country;
    private String isoAlphaCode2;
    private String isoAlphaCode3;
    private List<String> languages;
    private String currency;
    private List<String> timezones;
    private String estimatedDistance;
}