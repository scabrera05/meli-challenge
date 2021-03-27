package com.meli.challenge.service;

import com.meli.challenge.domain.dto.*;
import com.meli.challenge.service.countryinformation.CountryInformationService;
import com.meli.challenge.service.currencyconversion.CurrencyInformationService;
import com.meli.challenge.service.ipgeolocation.IpGeoLocationService;
import com.meli.challenge.service.location.LocationService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class TraceServiceImpl implements TraceService {

    private final CountryInformationService countryInformationService;
    private final CurrencyInformationService currencyInformationService;
    private final LocationService locationService;
    private final IpGeoLocationService ipGeoLocationService;

    public TraceServiceImpl(CountryInformationService countryInformationService,
                            CurrencyInformationService currencyInformationService,
                            LocationService locationService,
                            IpGeoLocationService geoLocationService) {
        this.countryInformationService = countryInformationService;
        this.currencyInformationService = currencyInformationService;
        this.locationService = locationService;
        this.ipGeoLocationService = geoLocationService;
    }

    private static String getActualDateTimeInUTCFormatted() {
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return df1.format(new Date());
    }

    @Override
    public TraceResponseDto traceIp(String ip) {

        IpLocationInformationDto ipLocationInfo = null;

        try {
            // Obtener información de país por IP
            ipLocationInfo = ipGeoLocationService.getIpLocationInformationDto(ip);
        } catch (HttpClientErrorException httpClientErrorException) {
            if (httpClientErrorException.getStatusCode().is4xxClientError()) {
                // No se encuentra información geográfica de la IP
                return null;
            }
        }

        // Obtener información de pais
        CountryInformationDto country = countryInformationService.getCountryInformationByIsoAlphaCode3(ipLocationInfo.getCountryIsoAlphaCode3());

        // Obtener información de moneda
        CurrencyInformationDto currency = currencyInformationService.getCurrencyInformation(country.getCurrencyCode());

        // Save location distance information
        locationService.saveLocation(
                country.getCountryIsoAlphaCode3(),
                country.getName(),
                country.getDistanceFromBuenosAires());

        TraceResponseDto traceResponse = new TraceResponseDto();
        traceResponse.setIp(ip);
        traceResponse.setIsoAlphaCode3(ipLocationInfo.getCountryIsoAlphaCode3());
        traceResponse.setIsoAlphaCode2(ipLocationInfo.getCountryIsoAlphaCode2());
        traceResponse.setCountry(country.getName());
        traceResponse.setDate(getActualDateTimeInUTCFormatted());
        traceResponse.setLanguages(country.getLanguages());
        traceResponse.setTimezones(country.getTimezones());
        traceResponse.setCurrency(String.format("%S (1 %S = %s %S)", country.getCurrencyCode(), currency.getBase(), currency.getRate(), country.getCurrencyCode()));
        traceResponse.setEstimatedDistance(String.format("%s Kms", (long) country.getDistanceFromBuenosAires()));

        return traceResponse;
    }

    @Override
    public StatsResponseDto getStatistics() {
        Optional<Integer> minDistanceFromBuenosAires = locationService.getMinDistanceFromBuenosAires();
        Optional<Integer> maxDistanceFromBuenosAires = locationService.getMaxDistanceFromBuenosAires();
        Optional<Integer> avgDistanceInvocations = locationService.getAvgDistanceInvocations();

        StatsResponseDto stats = new StatsResponseDto();
        stats.setMinDistanceFromBuenosAires(minDistanceFromBuenosAires);
        stats.setMaxDistanceFromBuenosAires(maxDistanceFromBuenosAires);
        stats.setAvgDistanceInvocations(avgDistanceInvocations);

        return stats;
    }

}
