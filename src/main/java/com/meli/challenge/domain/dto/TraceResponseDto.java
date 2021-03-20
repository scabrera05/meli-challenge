package com.meli.challenge.domain.dto;

import java.util.Date;
import java.util.List;

public class TraceResponseDto {

    private String ip;
    private Date date;
    private String country;
    private String isoAlphaCode2;
    private String isoAlphaCode3;
    private List<String> languages;
    private String currency;
    private List<String> times;
    private String estimatedDistance;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIsoAlphaCode2() {
        return isoAlphaCode2;
    }

    public void setIsoAlphaCode2(String isoAlphaCode2) {
        this.isoAlphaCode2 = isoAlphaCode2;
    }

    public String getIsoAlphaCode3() {
        return isoAlphaCode3;
    }

    public void setIsoAlphaCode3(String isoAlphaCode3) {
        this.isoAlphaCode3 = isoAlphaCode3;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public String getEstimatedDistance() {
        return estimatedDistance;
    }

    public void setEstimatedDistance(String estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }
}