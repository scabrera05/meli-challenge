package com.meli.challenge.service.currencyconversion;

import com.meli.challenge.domain.dto.CurrencyInformationDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class CurrencyInformationServiceImpl implements CurrencyInformationService {

    @Cacheable("currencies")
    public CurrencyInformationDto getCurrencyInformation(String currencyCode) {

        RestTemplate restTemplate = new RestTemplate();

        // TODO: pasar datos a configuración
        final String currencyApiUrl = "http://data.fixer.io/api/latest";
        final String accessKey = "39cab8e7539ea6beaa52b0a23d30522e";
        final String baseCurrency = "EUR";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(currencyApiUrl)
                .queryParam("access_key", accessKey)
                .queryParam("base", baseCurrency)
                .queryParam("symbols", currencyCode);

        CurrencyApiResponse currencyResult = restTemplate.getForObject(builder.toUriString(), CurrencyApiResponse.class);

        CurrencyInformationDto currencyInformation = new CurrencyInformationDto();
        currencyInformation.setCurrencyCode(currencyCode);
        currencyInformation.setBase(baseCurrency);
        currencyInformation.setRate(currencyResult.rates.get(currencyCode));

        return currencyInformation;
    }

    @Getter
    @Setter
    private static class CurrencyApiResponse {
        private boolean success;
        private int timestamp;
        private String base;
        private String date;
        private Map<String, String> rates;
    }

}
