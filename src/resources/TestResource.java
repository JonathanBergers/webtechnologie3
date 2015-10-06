package resources;

import model.Model;
import test.TestObject;

import javax.ws.rs.Path;
import java.util.ArrayList;

/**
 * Created by jonathan on 6-10-15.
 */
@Path("/test")
public class TestResource extends BaseResource<TestObject> {


    @Override
    protected Class getResourceClass() {
        return TestObject.class;
    }

    @Override
    protected ArrayList<TestObject> getAllResources() {

        // maak een lijst met test objecten om te filteren.
        ArrayList<TestObject> testObjects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testObjects.add(new TestObject("CORRECT"));
            testObjects.add(new TestObject("INCORRECT"));
        }
        return testObjects;
    }
}
