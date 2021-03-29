package com.meli.challenge.service.location;

import com.meli.challenge.repositories.Location;
import com.meli.challenge.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public void saveLocation(String countryIsoAlphaCode3, String countryName, double distanceFromBuenosAires) {

        Location location = locationRepository.findByCountryIsoAlphaCode3(countryIsoAlphaCode3);
        if (location != null) { // Increment invocation
            int invocations = location.getInvocationsCount();
            location.setInvocationsCount(++invocations);
        } else { // Add new location value
            location = new Location();
            location.setCountryIsoAlphaCode3(countryIsoAlphaCode3);
            location.setCountryName(countryName);
            location.setDistanceFromBuenosAires((int) distanceFromBuenosAires);
            location.setInvocationsCount(1);
        }

        locationRepository.save(location);
    }

    @Override
    public Optional<Integer> getMaxDistanceFromBuenosAires() {
        return locationRepository.getMaxDistanceFromBuenosAires();
    }

    @Override
    public Optional<Integer> getMinDistanceFromBuenosAires() {
        return locationRepository.getMinDistanceFromBuenosAires();
    }

    @Override
    public Optional<Integer> getAvgDistanceInvocations() {
        return locationRepository.getAvgDistanceInvocations();
    }
}
