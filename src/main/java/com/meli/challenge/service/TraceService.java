package com.meli.challenge.service;

import com.meli.challenge.domain.dto.StatsResponseDto;
import com.meli.challenge.domain.dto.TraceResponseDto;

public interface TraceService {
    TraceResponseDto traceIp(String ip);

    StatsResponseDto getStatistics();
}
