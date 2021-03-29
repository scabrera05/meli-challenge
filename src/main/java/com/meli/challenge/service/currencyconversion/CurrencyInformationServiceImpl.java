package com.meli.challenge.service.currencyconversion;

import com.meli.challenge.domain.dto.CurrencyInformationDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class CurrencyInformationServiceImpl implements CurrencyInformationService {

    @Value("${fixerCurrencyService.baseUrl}")
    private String currencyApiUrl;

    @Value("${fixerCurrencyService.accessKey}")
    private String accessKey;

    @Value("${fixerCurrencyService.baseCurrency}")
    private String baseCurrency;

    private final RestTemplate restTemplate;

    public CurrencyInformationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("currencies")
    public CurrencyInformationDto getCurrencyInformation(String currencyCode) {

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
