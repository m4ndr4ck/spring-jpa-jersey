package br.com.cinq.cities.service;

import br.com.cinq.cities.model.City;
import br.com.cinq.cities.model.Country;

import java.util.List;

/**
 * Created by dvsjunior on 14/02/2018.
 */
public interface CitiesService {
    public List<City> getAllCities();

    public List<City> findByCountry(Country country);

    public List<Country> getCountries(String name);
}
