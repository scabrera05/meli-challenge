package com.meli.challenge.repositories;

import com.meli.challenge.domain.dto.CurrencyInformationDto;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<CurrencyInformationDto, String> {
}
