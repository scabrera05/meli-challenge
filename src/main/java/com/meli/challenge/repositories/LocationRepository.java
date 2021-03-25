package com.meli.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findByCountryIsoAlphaCode3(String countryIsoAlphaCode3);

    @Query("SELECT MAX(l.distanceFromBuenosAires) FROM Location l")
    Optional<Integer> getMaxDistanceFromBuenosAires();

    @Query("SELECT MIN(l.distanceFromBuenosAires) FROM Location l")
    Optional<Integer> getMinDistanceFromBuenosAires();

    @Query("SELECT SUM(l.distanceFromBuenosAires * l.invocationsCount) / SUM(l.invocationsCount) FROM Location l")
    Optional<Integer> getAvgDistanceInvocations();
}
