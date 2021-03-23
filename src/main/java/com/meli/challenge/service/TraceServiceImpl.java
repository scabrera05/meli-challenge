package com.meli.challenge.service;

import com.meli.challenge.domain.dto.CountryInformationDto;
import com.meli.challenge.domain.dto.CurrencyInformationDto;
import com.meli.challenge.domain.dto.IpLocationInformationDto;
import com.meli.challenge.domain.dto.TraceResponseDto;
import com.meli.challenge.service.countryinformation.CountryInformationService;
import com.meli.challenge.service.currencyconversion.CurrencyInformationService;
import com.meli.challenge.service.ipgeolocation.IpGeoLocationService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TraceServiceImpl implements TraceService {

    private final CountryInformationService countryInformationService;
    private final CurrencyInformationService currencyInformationService;


    public TraceServiceImpl(CountryInformationService countryInformationService, CurrencyInformationService currencyInformationService) {
        this.countryInformationService = countryInformationService;
        this.currencyInformationService = currencyInformationService;
    }

    public TraceResponseDto traceIp(String ip) {

        // Obtener información de país por IP
        IpGeoLocationService geoLocationService = new IpGeoLocationService();
        IpLocationInformationDto ipLocationInfo = geoLocationService.getIpLocationInformationDto(ip);

        // TODO: controlar resultado distinto de null
        // TODO: controlar posible excepciones con invocación

        // Obtener información de pais
        CountryInformationDto country = countryInformationService.getCountryInformationByIsoAlphaCode3(ipLocationInfo.getCountryIsoAlphaCode3());

        // Obtener información de moneda
        CurrencyInformationDto currency = currencyInformationService.getCurrencyInformation(country.getCurrencyCode());

        // Guardar información sobre llamada
        // TODO: implementar

        TraceResponseDto traceResponse = new TraceResponseDto();
        traceResponse.setIp(ip);
        traceResponse.setIsoAlphaCode3(ipLocationInfo.getCountryIsoAlphaCode3());
        traceResponse.setIsoAlphaCode2(ipLocationInfo.getCountryIsoAlphaCode2());
        traceResponse.setCountry(country.getName());
        traceResponse.setDate(new Date());
        traceResponse.setLanguages(country.getLanguages());
        traceResponse.setTimezones(country.getTimezones());
        traceResponse.setCurrency(String.format("%S (1 %S = %s %S)", country.getCurrencyCode(), currency.getBase(), currency.getRate(), country.getCurrencyCode()));
        traceResponse.setEstimatedDistance(String.format("%s Kms", (long) country.getDistanceFromBuenosAires()));

        return traceResponse;
    }

}
