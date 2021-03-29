package com.meli.challenge.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class StatsResponseDto {
    private Optional<Integer> minDistanceFromBuenosAires;
    private Optional<Integer> maxDistanceFromBuenosAires;
    private Optional<Integer> avgDistanceInvocations;
}
