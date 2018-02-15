package br.com.cinq.cities.endpoint;

import java.net.URI;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import br.com.cinq.cities.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.cinq.cities.model.City;
import br.com.cinq.cities.service.CitiesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@Path("/cities")
public class CitiesEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(CitiesEndpoint.class);
    @Autowired
    private CitiesService citiesService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticleDetails(
            @QueryParam("country") String name) {
        List<City> list = null;
        if(name!=null) {
            List<Country> countries = citiesService.getCountries(name);
            for (Country country : countries) {
                System.out.println(country.getName());
                list = citiesService.findByCountry(country);
            }
        }else{
        list =  citiesService.getAllCities();
        }
        return Response.ok(list).build();
    }
} 