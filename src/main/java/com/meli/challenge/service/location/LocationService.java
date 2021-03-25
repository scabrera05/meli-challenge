package com.meli.challenge.service.location;

import java.util.Optional;

public interface LocationService {
    void saveLocation(String countryIsoAlphaCode3, String countryName, double distanceFromBuenosAires);

    Optional<Integer> getMaxDistanceFromBuenosAires();

    Optional<Integer> getMinDistanceFromBuenosAires();

    Optional<Integer> getAvgDistanceInvocations();
}
