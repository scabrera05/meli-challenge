package com.meli.challenge.service;

import com.meli.challenge.domain.dto.CountryInformationDto;
import com.meli.challenge.domain.dto.IpLocationInformationDto;
import com.meli.challenge.domain.dto.TraceResponseDto;
import com.meli.challenge.domain.model.IpRequestModel;
import com.meli.challenge.service.countryinformation.CountryInformationService;
import com.meli.challenge.service.ipgeolocation.IpGeoLocationService;

public class TraceService {

    public TraceResponseDto traceIp(IpRequestModel ip) {

        TraceResponseDto result = new TraceResponseDto();

        // Obtener información de país por IP
        IpGeoLocationService geoLocationService = new IpGeoLocationService();
        IpLocationInformationDto ipLocationInfo = geoLocationService.getIpLocationInformationDto(ip);

        // TODO: controlar resultado distinto de null
        // TODO: controlar posible excepciones con invocación

        String countryCode3 = ipLocationInfo.getCountryIsoAlphaCode3();
        String countryCode2 = ipLocationInfo.getCountryIsoAlphaCode2();

        result.setIsoAlphaCode2(countryCode2);
        result.setIsoAlphaCode3(countryCode3);

        // Obtener información de pais
        CountryInformationService countryInformationService = new CountryInformationService();
        CountryInformationDto countryInformation = countryInformationService.getCountryInformation(ipLocationInfo.getCountryIsoAlphaCode3());

        // Guardar información sobre llamada

        return result;
    }
}
