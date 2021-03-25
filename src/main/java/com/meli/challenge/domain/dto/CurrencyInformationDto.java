package com.meli.challenge.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
public class CurrencyInformationDto implements Serializable {
    @Id
    private String currencyCode;
    private String base;
    private String rate;
}
