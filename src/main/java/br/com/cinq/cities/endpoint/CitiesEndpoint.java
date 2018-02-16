package br.com.cinq.cities.endpoint;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cinq.cities.model.City;
import br.com.cinq.cities.model.Country;
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

        List<City> cities = new ArrayList<>();

        try {
            //Check whether request country id exists on database before to add new city
            if(newcity.
                    stream().
                    map(city -> city.getCountry()).
                    anyMatch(country -> citiesService.findCountryById(country.getId())==null))
            throw new Exception("Country id doesn't exist - Request not processed");

            //Prevent the request from add the repeated city and country id
            BiPredicate<City, City> p = (cityobj, city) -> (cityobj.getCountry().getId() == city.getCountry().getId()) &&
            (cityobj.getName().equals(city.getName()));
            if(newcity.
                    stream().anyMatch(city -> {
                    boolean ret = false;
                    for(City cityobj : citiesService.findCityByName(city.getName()))
                        if(p.test(cityobj, city)){
                        ret = true;
                        break;
                        }
                    return ret;}
            ))
            throw new Exception("City name and country id already exist");

            //Add city removing duplicates from JSON request
            newcity.stream().collect(
                    Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(City::getName)))).
                    stream()
                    .forEach(city -> {
                citiesService.addCity(city);
                cities.add(city);
            });

        }catch (Exception exception){

            return Response.ok("Request error: "+ exception.getMessage()).build();

        }

        return Response.ok(cities).type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("/add/country")
    public Response addCountry(@RequestBody List<Country> newcountry) {

        List<Country> countries = new ArrayList<>();

        try {
            //Check whether country name already exists on database
            if (newcountry.
                    stream().
                    anyMatch(country -> citiesService.findCountryByName(country.getName())!=null))
                throw new Exception("Country name already exists");

            //Add country removing duplicates from JSON request
            newcountry.stream().collect(
                    Collectors.toCollection(()->new TreeSet<>(Comparator.comparing(Country::getName)))).
                    stream().
                    forEach(country ->{
                    citiesService.addCountry(country);
                    countries.add(country);});

        } catch (Exception exception) {
            return Response.ok("Request error: " + exception.getMessage()).build();
        }
        return Response.ok(countries).type(MediaType.APPLICATION_JSON).build();
    }

} 