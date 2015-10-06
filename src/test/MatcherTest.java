package test;

import model.Matcher;
import model.Oefening;
import model.QueryError;
import model.QueryParam;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by jonathan on 6-10-15.
 */
public class MatcherTest {





    @Test
    public void testGetValidQueries(){



        // maak nieuwe matcher
        Matcher<TestObject> testObjectMatcher = new Matcher<>(TestObject.class);



        // maak een paar query parameters
        ArrayList<QueryParam> queryParams = new ArrayList<>();
        // goede qparameter
        queryParams.add(new QueryParam("name", "jo"));

        // slechte queryparameter
        queryParams.add(new QueryParam("invalid field", "invalid value"));


        // maak een nieuw test object om mee te matchen
        TestObject testObject = new TestObject("jonathan");

        // kijk of de query parameters overeenkomen met de attributen van het testobject.
        // als het goed is zal er maar een item in de map moeten zitten en zal er een error in de matcher zijn
        HashMap<QueryParam, Method> methodParamMap = testObjectMatcher.getValidQueries(new HashMap<QueryParam, Method>(), queryParams);


        assertThat(methodParamMap.size(), is(1));
        assertThat(testObjectMatcher.getErrors().size(), is(1));

    }

    @Test
    public void testGetValidQueriesAlleenStringOfInt(){



        // maak nieuwe matcher
        Matcher<TestObject2> testObjectMatcher = new Matcher<>(TestObject2.class);



        // maak een paar query parameters
        ArrayList<QueryParam> queryParams = new ArrayList<>();
        // goede qparameter
        queryParams.add(new QueryParam<String>("name", "jo"));

        // slechte queryparameter
        queryParams.add(new QueryParam<String>("invalid field", "invalid value"));


        // maak een nieuw test object om mee te matchen
        TestObject testObject = new TestObject("jonathan");
        TestObject2 testObject2 = new TestObject2("bergers", testObject);

        // kijk of de query parameters overeenkomen met de attributen van het testobject.
        // als het goed is zal er maar een item in de map moeten zitten en zal er een error in de matcher zijn
        // het testObject wordt niet meegenomen omdat de matcher alleen maar op strings of integers kan matchen
        HashMap<QueryParam, Method> methodParamMap = testObjectMatcher.getValidQueries(new HashMap<QueryParam, Method>(), queryParams);


        assertThat(methodParamMap.size(), is(1));
        assertThat(testObjectMatcher.getErrors().size(), is(1));

    }
}
