package com.meli.challenge.domain.dto;

import java.util.List;

public class CountryInformationDto {
    private String name;
    private List<String> languages;
    private String currencyCode;
    private List<String> timezones;
    private double distanceFromBuenosAires;

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public double getDistanceFromBuenosAires() {
        return distanceFromBuenosAires;
    }

    public void setDistanceFromBuenosAires(double distanceFromBuenosAires) {
        this.distanceFromBuenosAires = distanceFromBuenosAires;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
