package com.meli.challenge.service.currencyconversion;

import com.meli.challenge.domain.dto.CurrencyInformationDto;

public interface CurrencyInformationService {
    CurrencyInformationDto getCurrencyInformation(String currencyCode);
}
