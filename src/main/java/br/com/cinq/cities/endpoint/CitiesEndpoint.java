package br.com.cinq.cities.endpoint;

import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cinq.cities.model.City;
import br.com.cinq.cities.model.Country;
import br.com.cinq.cities.service.CitiesServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.cinq.cities.service.CitiesService;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@Path("/cities")
public class CitiesEndpoint {

    @Autowired
    private CitiesService citiesService;

    @GET
    public Response getCities(@QueryParam("country") String country) {

        //If country is null get all city/countries. Otherwise it filters by country.
        List<?> cities = country!=null ? citiesService.getCountries(country)
                .stream()
                .map(name -> citiesService.findByCountry(name)).flatMap(x -> x.stream())
                .collect(Collectors.toList()) : citiesService.getAllCities();

        return Response.
                ok(cities.isEmpty() ? "Can't process your request": cities).
                type(cities.isEmpty() ? MediaType.TEXT_PLAIN: MediaType.APPLICATION_JSON).
                build();
    }

    @POST
    @Path("/add/city")
    public Response addCity(@RequestBody List<City> newcity) {

        try {
            //Check whether request country id exists on database before to add new city
            if(newcity.
                    stream().
                    map(city -> city.getCountry()).
                    anyMatch(country -> citiesService.findCountryById(country.getId()) == null))
            throw new CitiesServiceException("Country id doesn't exist");

            //Add city
            newcity.stream().forEach(city -> citiesService.addCity(city));

        }catch (CitiesServiceException citiesServiceException){
            return Response.ok("Request error: "+ citiesServiceException.getMessage()).build();
        }

        return Response.ok("Success!").build();
    }

    @POST
    @Path("/add/country")
    public Response addCountry(@RequestBody List<Country> newcountry) {
        try {
            if (newcountry.
                    stream().
                    noneMatch(city -> citiesService.findCountryByName(city.getName()) == null))
                throw new CitiesServiceException("Country name already exists");

            //Add Country
            newcountry.forEach(country -> citiesService.addCountry(country));
        } catch (CitiesServiceException citiesServiceException) {
            return Response.ok("Request error: " + citiesServiceException.getMessage()).build();
        }
        return Response.ok("Success!").build();
    }

} 