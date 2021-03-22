package com.meli.challenge.service.ipgeolocation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.challenge.domain.dto.IpLocationInformationDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class IpGeoLocationService {

    // TODO: inyectar RestTemplate

    public IpLocationInformationDto getIpLocationInformationDto(String ip) {

        RestTemplate restTemplate = new RestTemplate();

        Ip2CountryResult ip2CountryResult = restTemplate.getForObject(String.format("https://api.ip2country.info/ip?%s", ip), Ip2CountryResult.class);

        IpLocationInformationDto ipLocationInformation = new IpLocationInformationDto();
        ipLocationInformation.setCountryName(ip2CountryResult.getCountryName());
        ipLocationInformation.setCountryIsoAlphaCode2(ip2CountryResult.getCountryIsoAlphaCode2());
        ipLocationInformation.setCountryIsoAlphaCode3(ip2CountryResult.getCountryIsoAlphaCode3());

        return ipLocationInformation;

    }

    private static class Ip2CountryResult {

        private String countryName;
        private String countryEmoji;
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

        public String getCountryEmoji() {
            return countryEmoji;
        }

        public void setCountryEmoji(String countryEmoji) {
            this.countryEmoji = countryEmoji;
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
}
