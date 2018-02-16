package br.com.cinq.cities.endpoint.test;

import br.com.cinq.cities.application.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import br.com.cinq.cities.model.City;
import org.springframework.web.util.UriComponentsBuilder;
import org.junit.experimental.categories.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("unit")
@Category(IntegrationTest.class)
public class EndpointTest {
    Logger logger = LoggerFactory.getLogger(EndpointTest.class);

    private final String localhost = "http://localhost:";

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetCities() throws InterruptedException {
        String country = "France";

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.localhost + this.port + "/rest/cities/")
                .queryParam("country", country);

        HttpEntity<?> model = new HttpEntity<>(headers);

        ResponseEntity<City[]> response = this.restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET,
                model, City[].class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        Thread.sleep(2000L);

        City[] cities = response.getBody();

        Assert.assertEquals(2, cities.length);

    }
}
