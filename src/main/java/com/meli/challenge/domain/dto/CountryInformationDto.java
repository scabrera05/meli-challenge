package com.meli.challenge.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CountryInformationDto implements Serializable {
    @Id
    private String countryIsoAlphaCode3;
    private String name;
    private List<String> languages;
    private String currencyCode;
    private List<String> timezones;
    private int distanceFromBuenosAires;
}
