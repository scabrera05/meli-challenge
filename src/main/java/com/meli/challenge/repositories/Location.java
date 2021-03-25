package com.meli.challenge.repositories;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Column(name = "country_iso_alpha_code3")
    private String countryIsoAlphaCode3;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "distance_from_bs_as")
    private int distanceFromBuenosAires;

    @Column(name = "invocations_count")
    private int invocationsCount;
}
