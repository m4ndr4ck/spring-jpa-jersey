package br.com.cinq.cities.application;

import javax.ws.rs.ApplicationPath;

import br.com.cinq.cities.endpoint.CitiesEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Register Jersey modules
 * @author Adriano Kretschmer
 */
@Configuration
@ApplicationPath("rest")
public class Config extends ResourceConfig {

    public Config() {
        register(CitiesEndpoint.class);
        //		packages("br.com.cinq.cities.service");
        //		property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }


}