package utils;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by jonathan on 30-9-15.
 */
@ApplicationPath("notflix")
public class RestConfig extends ResourceConfig {


    public RestConfig() {
        packages("resources");
    }
}
