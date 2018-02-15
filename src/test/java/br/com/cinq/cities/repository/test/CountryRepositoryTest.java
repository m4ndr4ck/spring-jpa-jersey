package br.com.cinq.cities.repository.test;

import br.com.cinq.cities.application.Application;
import br.com.cinq.cities.model.Country;
import br.com.cinq.cities.repository.CountryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("unit")
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void testGelAllCountries() {

        Assert.assertNotNull(countryRepository);

        long count = countryRepository.count();

        Assert.assertTrue(count > 0);

        List<Country> countries = countryRepository.findAll();

        Assert.assertEquals((int) count, countries.size());
    }

    @Test
    public void testFindOneCountry() {

        Assert.assertNotNull(countryRepository);

        List<Country> countries = countryRepository.findByNameIgnoreCaseContaining("Fra");

        Assert.assertEquals(1, countries.size());

    }

}
