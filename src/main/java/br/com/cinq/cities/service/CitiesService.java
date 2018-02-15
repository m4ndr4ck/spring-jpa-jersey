package br.com.cinq.cities.service;

import br.com.cinq.cities.model.City;
import br.com.cinq.cities.model.Country;

import java.util.List;

/**
 * Created by dvsjunior on 14/02/2018.
 */
public interface CitiesService {

    List<City> getAllCities();

    List<City> findByCountry(Country name);

    List<Country> getCountries(String country);

    Country findCountryById(long id);

    Country findCountryByName(String name);

    City findCityByName(String name);

    void addCity(City city);

    void addCountry(Country country);

}
