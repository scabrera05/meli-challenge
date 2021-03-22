package com.meli.challenge.service.currencyconversion;

import com.meli.challenge.domain.dto.CurrencyInformationDto;
import org.springframework.beans.BeanUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.beans.PropertyDescriptor;
import java.util.Map;
import java.util.stream.Collectors;

public class CurrencyInformationService {
    public CurrencyInformationDto getCurrencyInformation(String currency) {

        RestTemplate restTemplate = new RestTemplate();

        // TODO: pasar datos a configuración
        final String currencyApiUrl = "http://data.fixer.io/api/latest";
        final String accessKey = "39cab8e7539ea6beaa52b0a23d30522e";
        final String baseCurrency = "EUR";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(currencyApiUrl)
                .queryParam("access_key", accessKey)
                .queryParam("base", baseCurrency)
                .queryParam("symbols", currency);

        CurrencyApiResponse currencyResult = restTemplate.getForObject(builder.toUriString(), CurrencyApiResponse.class);

        CurrencyInformationDto currencyInformation = new CurrencyInformationDto();
        currencyInformation.setBase(baseCurrency);
        currencyInformation.setRate(currencyResult.rates.get(currency));

        return currencyInformation;
    }

    private static class CurrencyApiResponse {
        private boolean success;
        private int timestamp;
        private String base;
        private String date;
        private Map<String,String> rates;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Map<String, String> getRates() {
            return rates;
        }

        public void setRates(Map<String, String> rates) {
            this.rates = rates;
        }
    }


}
