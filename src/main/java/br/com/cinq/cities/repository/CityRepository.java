package br.com.cinq.cities.repository;

import br.com.cinq.cities.model.City;
import br.com.cinq.cities.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by dvsjunior on 14/02/2018.
 */
public interface CityRepository extends JpaRepository<City, Long> {

    @Override
    List<City> findAll();
    List<City> findByCountry(Country country);
}