package com.meli.challenge.service.countryinformation;

import com.meli.challenge.domain.dto.CountryInformationDto;

public interface CountryInformationService {
    CountryInformationDto getCountryInformationByIsoAlphaCode3(String countryIsoAlphaCode3);
}
