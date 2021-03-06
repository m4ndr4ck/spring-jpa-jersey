package br.com.cinq.cities.repository;

import br.com.cinq.cities.model.City;
import br.com.cinq.cities.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by dvsjunior on 14/02/2018.
 */
public interface CountryRepository extends JpaRepository<Country, Long>{

    List<Country> findByNameIgnoreCaseContaining(String country);

    Country findByName(String country);

    Country findById(long id);



}
