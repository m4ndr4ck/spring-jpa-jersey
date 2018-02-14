package br.com.cinq.cities.endpoint;

import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.cinq.cities.model.City;
import br.com.cinq.cities.service.CitiesService;
@Component
@Path("/cities")
public class CitiesEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(CitiesEndpoint.class);
    @Autowired
    private CitiesService citiesService;
    @GET
    @Path("/details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticleDetails() {
        List<City> list = citiesService.getAllCities();
        return Response.ok(list).build();
    }
} 