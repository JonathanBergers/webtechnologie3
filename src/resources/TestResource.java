package resources;

import model.Model;
import test.TestObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by jonathan on 6-10-15.
 */
@Path("/test")
public class TestResource extends SearchableResource<TestObject> {


    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<TestObject> getTestObjects(){

        Model model = (Model) servletContext.getAttribute("model");


        // maak een lijst met test objecten om te filteren.
        ArrayList<TestObject> testObjects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testObjects.add(new TestObject("CORRECT"));
            testObjects.add(new TestObject("INCORRECT"));
        }


        return getResources(TestObject.class, testObjects).getResults();

    }
}
