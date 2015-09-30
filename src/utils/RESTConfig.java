package utils;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by jonathan on 30-9-15.
 */
@ApplicationPath("api")
public class RESTConfig extends ResourceConfig {

    public RESTConfig() {
        super();
        register(com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider.class);
        packages("resources");

    }
}
