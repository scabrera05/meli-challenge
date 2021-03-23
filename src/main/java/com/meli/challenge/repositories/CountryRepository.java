package com.meli.challenge.repositories;

import com.meli.challenge.domain.dto.CountryInformationDto;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<CountryInformationDto, String> {
}
