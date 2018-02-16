package br.com.cinq.cities.service;

import br.com.cinq.cities.model.City;
import br.com.cinq.cities.model.Country;
import br.com.cinq.cities.repository.CityRepository;
import br.com.cinq.cities.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by dvsjunior on 14/02/2018.
 */
@Service
public class CitiesServiceImpl implements CitiesService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    public List<City> getAllCities(){
        return cityRepository.findAll();
    }

    public List<City> findByCountry(Country name){
        return cityRepository.findByCountry(name);
    }

    public List<Country> getCountries(String country){
        return countryRepository.findByNameIgnoreCaseContaining(country);
    }

    public Country findCountryById(long id){
        return countryRepository.findById(id);
    }

    public Country findCountryByName(String name){
        return countryRepository.findByName(name);
    }


    public List<City> findCityByName(String name){
        return cityRepository.findByNameIgnoreCase(name);
    }

    public void addCity(City city){
        cityRepository.save(city);
    }

    public void addCountry(Country country){
        countryRepository.save(country);
    }

}
