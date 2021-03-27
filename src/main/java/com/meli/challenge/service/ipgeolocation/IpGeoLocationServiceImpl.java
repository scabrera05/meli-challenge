package com.meli.challenge.service.ipgeolocation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.challenge.domain.dto.IpLocationInformationDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class IpGeoLocationServiceImpl implements IpGeoLocationService {

    @Value("${ip2CountryService.baseUrl}")
    private String ip2CountryBaseUrl;

    private final RestTemplate restTemplate;

    public IpGeoLocationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public IpLocationInformationDto getIpLocationInformationDto(String ip) {

        Ip2CountryResult ip2CountryResult = restTemplate.getForObject(ip2CountryBaseUrl + "/ip?" + ip, Ip2CountryResult.class);

        IpLocationInformationDto ipLocationInformation = new IpLocationInformationDto();
        ipLocationInformation.setCountryName(ip2CountryResult.getCountryName());
        ipLocationInformation.setCountryIsoAlphaCode2(ip2CountryResult.getCountryIsoAlphaCode2());
        ipLocationInformation.setCountryIsoAlphaCode3(ip2CountryResult.getCountryIsoAlphaCode3());

        return ipLocationInformation;

    }

    @Getter
    @Setter
    private static class Ip2CountryResult {

        private String countryName;
        private String countryEmoji;
        @JsonProperty("countryCode")
        private String countryIsoAlphaCode2;
        @JsonProperty("countryCode3")
        private String countryIsoAlphaCode3;
    }
}
