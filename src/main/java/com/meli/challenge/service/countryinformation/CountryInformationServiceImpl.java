package com.meli.challenge.service.countryinformation;

import com.meli.challenge.domain.dto.CountryInformationDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryInformationServiceImpl implements CountryInformationService {

    public static final double BUENOS_AIRES_LATITUDE = -34.599722;
    public static final double BUENOS_AIRES_LONGITUDE = -58.381944;
    private final RestTemplate restTemplate;
    @Value("${restCountriesService.baseUrl}")
    public String restCountryServiceBaseUrl;

    public CountryInformationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Información basada en https://www.geodatasource.com/resources/tutorials/how-to-calculate-the-distance-between-2-locations-using-java/
     * <p>
     * Calculate distance between two points in latitude and longitude
     * <p>
     * lat1, lon1 = Latitude and Longitude of point 1 in decimal degrees
     * lat2, lon2 = Latitude and Longitude of point 2 in decimal degrees
     * unit = the unit you desire for results where ‘M’ is the statute miles (default), ‘K’ is kilometers and ‘N’ is nautical miles
     *
     * @returns Distance in specified unit
     */
    private static double distanceBetweenCoordinates(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) +
                    Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515; // 'M'
            if (unit.equals("K")) {
                dist = dist * 1.609344; // returns
            } else if (unit.equals("N")) {
                dist = dist * 0.8684; //
            }
            return (dist);
        }
    }

    @Cacheable("countries")
    public CountryInformationDto getCountryInformationByIsoAlphaCode3(String countryIsoAlphaCode3) {

        String countryApiUrl = restCountryServiceBaseUrl + '/' + countryIsoAlphaCode3;
        CountryApiResponse countryResult = restTemplate.getForObject(countryApiUrl, CountryApiResponse.class);

        CountryInformationDto countryInformation = new CountryInformationDto();
        countryInformation.setName(countryResult.getName());
        countryInformation.setTimezones(countryResult.getTimezones());
        countryInformation.setCurrencyCode(countryResult.currencies.get(0).getCode());
        countryInformation.setCountryIsoAlphaCode3(countryIsoAlphaCode3);

        countryInformation.setLanguages(countryResult.getLanguages()
                .stream().map(language -> String.format("%s (%S)", language.getNativeName(), language.getIso639_1()))
                .collect(Collectors.toList()));

        // Distance from country to Buenos Aires
        double distanceFromCountryToBsAsInKms = distanceBetweenCoordinates(
                BUENOS_AIRES_LATITUDE,
                BUENOS_AIRES_LONGITUDE,
                countryResult.latlng.get(0),
                countryResult.getLatlng().get(1),
                "K");
        countryInformation.setDistanceFromBuenosAires((int) distanceFromCountryToBsAsInKms);

        return countryInformation;
    }

    @Getter
    @Setter
    private static class Currency {
        String code;
        String name;
        String symbol;
    }

    @Getter
    @Setter
    private static class Language {
        String iso639_1;
        String iso639_2;
        String name;
        String nativeName;
    }

    @Getter
    @Setter
    private static class Translations {
        String de;
        String es;
        String fr;
        String ja;
        String it;
        String br;
        String pt;
        String nl;
        String hr;
        String fa;
    }

    @Getter
    @Setter
    private static class RegionalBloc {
        String acronym;
        String name;
        List<String> otherAcronyms;
        List<String> otherNames;
    }

    @Getter
    @Setter
    private static class CountryApiResponse {
        String name;
        List<String> topLevelDomain;
        String alpha2Code;
        String alpha3Code;
        List<String> callingCodes;
        String capital;
        List<String> altSpellings;
        String region;
        String subregion;
        int population;
        List<Double> latlng;
        String demonym;
        double area;
        double gini;
        List<String> timezones;
        List<String> borders;
        String nativeName;
        String numericCode;
        List<Currency> currencies;
        List<Language> languages;
        Translations translations;
        String flag;
        List<RegionalBloc> regionalBlocs;
        String cioc;
    }

}
