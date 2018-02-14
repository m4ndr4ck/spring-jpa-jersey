package br.com.cinq.cities.service;

import br.com.cinq.cities.model.City;

import java.util.List;

/**
 * Created by dvsjunior on 14/02/2018.
 */
public interface CitiesService {
    public List<City> getAllCities();

    public List<City> findCity(String country);
}
