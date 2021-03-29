package com.meli.challenge.service.ipgeolocation;

import com.meli.challenge.domain.dto.IpLocationInformationDto;

public interface IpGeoLocationService {
    IpLocationInformationDto getIpLocationInformationDto(String ip);
}
