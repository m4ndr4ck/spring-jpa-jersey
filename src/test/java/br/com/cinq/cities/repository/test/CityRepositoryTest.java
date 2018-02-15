package br.com.cinq.cities.repository.test;

import br.com.cinq.cities.model.City;
import br.com.cinq.cities.model.Country;
import br.com.cinq.cities.repository.CityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cinq.cities.application.Application;

import java.util.List;
//import br.com.cinq.spring.data.sample.model.City;
//import br.com.cinq.spring.data.sample.model.Country;
//import br.com.cinq.spring.data.sample.repository.CityRepository;

/**
 * Eye candy: implements a sample in using JpaRespositories
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("unit")
public class CityRepositoryTest {

    @Autowired
    CityRepository cityRepository;

    @Test
    public void testQueryPerson() {

        Assert.assertNotNull(cityRepository);
        
        Assert.assertTrue(cityRepository.count()>0);

        Country country = new Country();
        country.setId(3); // Should be France

        List<City> list = cityRepository.findByCountry(country);

        Assert.assertEquals(2, list.size());
    }
}
