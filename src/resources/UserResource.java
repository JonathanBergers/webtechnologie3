package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by jonathan on 30-9-15.
 */
@Path("/users")
public class UserResource {


    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String sayHello() {
        return "Hello Jersey";
    }

}