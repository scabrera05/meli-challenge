package com.meli.challenge.service.ipgeolocation;

import com.meli.challenge.domain.dto.IpLocationInformationDto;
import com.meli.challenge.domain.model.IpRequestModel;

public class IpGeoLocationService {

    public IpLocationInformationDto getIpLocationInformationDto(IpRequestModel ip) {
        return new IpLocationInformationDto();
    }
}
