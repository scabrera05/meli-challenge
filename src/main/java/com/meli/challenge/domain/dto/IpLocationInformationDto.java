package com.meli.challenge.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IpLocationInformationDto {

    private String countryName;
    @JsonProperty("countryCode")
    private String countryIsoAlphaCode2;
    @JsonProperty("countryCode3")
    private String countryIsoAlphaCode3;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryIsoAlphaCode2() {
        return countryIsoAlphaCode2;
    }

    public void setCountryIsoAlphaCode2(String countryIsoAlphaCode2) {
        this.countryIsoAlphaCode2 = countryIsoAlphaCode2;
    }

    public String getCountryIsoAlphaCode3() {
        return countryIsoAlphaCode3;
    }

    public void setCountryIsoAlphaCode3(String countryIsoAlphaCode3) {
        this.countryIsoAlphaCode3 = countryIsoAlphaCode3;
    }

}
