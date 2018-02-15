package br.com.cinq.cities.service;

import br.com.cinq.cities.model.City;
import br.com.cinq.cities.model.Country;
import br.com.cinq.cities.repository.CityRepository;
import br.com.cinq.cities.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dvsjunior on 14/02/2018.
 */
@Service
public class CitiesServiceImpl implements CitiesService {

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    public List<City> findByCountry(Country country){
        return cityRepository.findByCountry(country);
    }

    public List<Country> getCountries(String name){
        return countryRepository.findByNameIgnoreCaseContaining(name);
    }
}
