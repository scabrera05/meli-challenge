package com.meli.challenge.service.countryinformation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.challenge.domain.dto.CountryInformationDto;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class CountryInformationService {

    public static final double BUENOS_AIRES_LATITUDE = -34.599722;
    public static final double BUENOS_AIRES_LONGITUDE = -58.381944;

    /**
     * Información basada en https://www.geodatasource.com/resources/tutorials/how-to-calculate-the-distance-between-2-locations-using-java/
     *
     * Calculate distance between two points in latitude and longitude
     *
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

    public CountryInformationDto getCountryInformationByIp(String countryIsoAlphaCode3) {

        RestTemplate restTemplate = new RestTemplate();

        // TODO: pasar datos a configuración
        String countryApiUrl = String.format("https://restcountries.eu/rest/v2/alpha/%s", countryIsoAlphaCode3);
        CountryApiResponse countryResult = restTemplate.getForObject(countryApiUrl, CountryApiResponse.class);

        CountryInformationDto countryInformation = new CountryInformationDto();
        countryInformation.setName(countryResult.getName());
        countryInformation.setTimezones(countryResult.getTimezones());
        // TODO: obtener todas las monedas
        countryInformation.setCurrencyCode(countryResult.currencies.get(0).getCode());

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
        countryInformation.setDistanceFromBuenosAires(distanceFromCountryToBsAsInKms);

        return countryInformation;
    }

    private static class Currency {
        String code;
        String name;
        String symbol;

        @JsonProperty("code")
        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @JsonProperty("name")
        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("symbol")
        public String getSymbol() {
            return this.symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }
    }

    private static class Language {
        String iso639_1;
        String iso639_2;
        String name;
        String nativeName;

        @JsonProperty("iso639_1")
        public String getIso639_1() {
            return this.iso639_1;
        }

        public void setIso639_1(String iso639_1) {
            this.iso639_1 = iso639_1;
        }

        @JsonProperty("iso639_2")
        public String getIso639_2() {
            return this.iso639_2;
        }

        public void setIso639_2(String iso639_2) {
            this.iso639_2 = iso639_2;
        }

        @JsonProperty("name")
        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("nativeName")
        public String getNativeName() {
            return this.nativeName;
        }

        public void setNativeName(String nativeName) {
            this.nativeName = nativeName;
        }
    }

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

        @JsonProperty("de")
        public String getDe() {
            return this.de;
        }

        public void setDe(String de) {
            this.de = de;
        }

        @JsonProperty("es")
        public String getEs() {
            return this.es;
        }

        public void setEs(String es) {
            this.es = es;
        }

        @JsonProperty("fr")
        public String getFr() {
            return this.fr;
        }

        public void setFr(String fr) {
            this.fr = fr;
        }

        @JsonProperty("ja")
        public String getJa() {
            return this.ja;
        }

        public void setJa(String ja) {
            this.ja = ja;
        }

        @JsonProperty("it")
        public String getIt() {
            return this.it;
        }

        public void setIt(String it) {
            this.it = it;
        }

        @JsonProperty("br")
        public String getBr() {
            return this.br;
        }

        public void setBr(String br) {
            this.br = br;
        }

        @JsonProperty("pt")
        public String getPt() {
            return this.pt;
        }

        public void setPt(String pt) {
            this.pt = pt;
        }

        @JsonProperty("nl")
        public String getNl() {
            return this.nl;
        }

        public void setNl(String nl) {
            this.nl = nl;
        }

        @JsonProperty("hr")
        public String getHr() {
            return this.hr;
        }

        public void setHr(String hr) {
            this.hr = hr;
        }

        @JsonProperty("fa")
        public String getFa() {
            return this.fa;
        }

        public void setFa(String fa) {
            this.fa = fa;
        }
    }

    private static class RegionalBloc {
        String acronym;
        String name;
        List<String> otherAcronyms;
        List<String> otherNames;

        @JsonProperty("acronym")
        public String getAcronym() {
            return this.acronym;
        }

        public void setAcronym(String acronym) {
            this.acronym = acronym;
        }

        @JsonProperty("name")
        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("otherAcronyms")
        public List<String> getOtherAcronyms() {
            return this.otherAcronyms;
        }

        public void setOtherAcronyms(List<String> otherAcronyms) {
            this.otherAcronyms = otherAcronyms;
        }

        @JsonProperty("otherNames")
        public List<String> getOtherNames() {
            return this.otherNames;
        }

        public void setOtherNames(List<String> otherNames) {
            this.otherNames = otherNames;
        }
    }

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

        @JsonProperty("name")
        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("topLevelDomain")
        public List<String> getTopLevelDomain() {
            return this.topLevelDomain;
        }

        public void setTopLevelDomain(List<String> topLevelDomain) {
            this.topLevelDomain = topLevelDomain;
        }

        @JsonProperty("alpha2Code")
        public String getAlpha2Code() {
            return this.alpha2Code;
        }

        public void setAlpha2Code(String alpha2Code) {
            this.alpha2Code = alpha2Code;
        }

        @JsonProperty("alpha3Code")
        public String getAlpha3Code() {
            return this.alpha3Code;
        }

        public void setAlpha3Code(String alpha3Code) {
            this.alpha3Code = alpha3Code;
        }

        @JsonProperty("callingCodes")
        public List<String> getCallingCodes() {
            return this.callingCodes;
        }

        public void setCallingCodes(List<String> callingCodes) {
            this.callingCodes = callingCodes;
        }

        @JsonProperty("capital")
        public String getCapital() {
            return this.capital;
        }

        public void setCapital(String capital) {
            this.capital = capital;
        }

        @JsonProperty("altSpellings")
        public List<String> getAltSpellings() {
            return this.altSpellings;
        }

        public void setAltSpellings(List<String> altSpellings) {
            this.altSpellings = altSpellings;
        }

        @JsonProperty("region")
        public String getRegion() {
            return this.region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        @JsonProperty("subregion")
        public String getSubregion() {
            return this.subregion;
        }

        public void setSubregion(String subregion) {
            this.subregion = subregion;
        }

        @JsonProperty("population")
        public int getPopulation() {
            return this.population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        @JsonProperty("latlng")
        public List<Double> getLatlng() {
            return this.latlng;
        }

        public void setLatlng(List<Double> latlng) {
            this.latlng = latlng;
        }

        @JsonProperty("demonym")
        public String getDemonym() {
            return this.demonym;
        }

        public void setDemonym(String demonym) {
            this.demonym = demonym;
        }

        @JsonProperty("area")
        public double getArea() {
            return this.area;
        }

        public void setArea(double area) {
            this.area = area;
        }

        @JsonProperty("gini")
        public double getGini() {
            return this.gini;
        }

        public void setGini(double gini) {
            this.gini = gini;
        }

        @JsonProperty("timezones")
        public List<String> getTimezones() {
            return this.timezones;
        }

        public void setTimezones(List<String> timezones) {
            this.timezones = timezones;
        }

        @JsonProperty("borders")
        public List<String> getBorders() {
            return this.borders;
        }

        public void setBorders(List<String> borders) {
            this.borders = borders;
        }

        @JsonProperty("nativeName")
        public String getNativeName() {
            return this.nativeName;
        }

        public void setNativeName(String nativeName) {
            this.nativeName = nativeName;
        }

        @JsonProperty("numericCode")
        public String getNumericCode() {
            return this.numericCode;
        }

        public void setNumericCode(String numericCode) {
            this.numericCode = numericCode;
        }

        @JsonProperty("currencies")
        public List<Currency> getCurrencies() {
            return this.currencies;
        }

        public void setCurrencies(List<Currency> currencies) {
            this.currencies = currencies;
        }

        @JsonProperty("languages")
        public List<Language> getLanguages() {
            return this.languages;
        }

        public void setLanguages(List<Language> languages) {
            this.languages = languages;
        }

        @JsonProperty("translations")
        public Translations getTranslations() {
            return this.translations;
        }

        public void setTranslations(Translations translations) {
            this.translations = translations;
        }

        @JsonProperty("flag")
        public String getFlag() {
            return this.flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        @JsonProperty("regionalBlocs")
        public List<RegionalBloc> getRegionalBlocs() {
            return this.regionalBlocs;
        }

        public void setRegionalBlocs(List<RegionalBloc> regionalBlocs) {
            this.regionalBlocs = regionalBlocs;
        }

        @JsonProperty("cioc")
        public String getCioc() {
            return this.cioc;
        }

        public void setCioc(String cioc) {
            this.cioc = cioc;
        }
    }

}
