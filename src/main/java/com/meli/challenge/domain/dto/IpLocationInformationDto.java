package com.meli.challenge.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IpLocationInformationDto {

    private String countryName;
    @JsonProperty("countryCode")
    private String countryIsoAlphaCode2;
    @JsonProperty("countryCode3")
    private String countryIsoAlphaCode3;
}
