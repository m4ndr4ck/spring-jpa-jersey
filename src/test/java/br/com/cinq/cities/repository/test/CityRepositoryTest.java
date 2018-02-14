package br.com.cinq.cities.repository.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cinq.cities.application.Application;
//import br.com.cinq.spring.data.sample.model.City;
//import br.com.cinq.spring.data.sample.model.Country;
//import br.com.cinq.spring.data.sample.repository.CityRepository;

/**
 * Eye candy: implements a sample in using JpaRespositories
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class CityRepositoryTest {

//    @Autowired
//    private CityRepository dao;

    @Test
    public void testQueryPerson() {

//        Assert.assertNotNull(dao);
        
//        Assert.assertTrue(dao.count()>0);

//        Country country = new Country();
//        country.setId(3); // Should be France

//        List<City> list = dao.findByCountry(country);

//        Assert.assertEquals(2, list.size());
    }
}
