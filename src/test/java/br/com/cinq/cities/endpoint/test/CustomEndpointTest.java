package br.com.cinq.cities.endpoint.test;

/**
 * Created by dvsjunior on 16/02/2018.
 */
import br.com.cinq.cities.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import org.junit.experimental.categories.Category;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Category(IntegrationTest.class)
public class CustomEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loadCityAndCountry() throws Exception {

        //Test country creation
        RequestBuilder addCountryTest = MockMvcRequestBuilders
                .post("/rest/cities/add/country")
                .content(createCountryInJson("Canada"))
                .contentType(MediaType.APPLICATION_JSON);

        //Test city creation (don't pass if country id doesn't exist on database)
        RequestBuilder addCityTest = MockMvcRequestBuilders
                .post("/rest/cities/add/city")
                .content(createCityInJson("Itaguai", 1))
                .contentType(MediaType.APPLICATION_JSON);

        List<RequestBuilder> requests = new ArrayList<>(Arrays.asList(
                addCountryTest,
                addCityTest
        ));

        List<MvcResult> results = requests
                .stream()
                .map((x) -> {
                    try {
                        return mockMvc.perform(x).andReturn();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } return null;})
                .collect(Collectors.toList());

        List<MockHttpServletResponse> responses = results
                .stream()
                .map(x -> x.getResponse())
                .collect(Collectors.toList());

        List<Integer> responseExpected = new ArrayList<>(Arrays.asList(
                200,
                    200
        ));

        List<Integer> responseResults = responses.stream().map(x -> {
            try {
                return x.getStatus();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;})
                .collect(Collectors.toList());

        //Test lambda expression
        BiConsumer<List<Integer>, List<Integer>> efetivaTeste =
                (expected, result) -> {
                    Iterator<Integer> expe = expected.iterator();
                    Iterator<Integer> resu = result.iterator();
                    while (expe.hasNext()) {
                        assertEquals(expe.next(), resu.next());
                    }

                };

        //Call lambda expression that perfoms de test assertion
        efetivaTeste.accept(responseExpected, responseResults);

    }

    private static String createCountryInJson (String country) {
        return "[{ \"name\": \"" + country + "\"}]";
    }

    private static String createCityInJson (String city, long countryid) {
        return "[{ \"name\": \""+city+"\", \"country\": { \"id\": "+countryid+" } }]";
    }


    }
