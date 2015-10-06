package test;

import model.*;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
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
    public void testOverall(){


        // maak nieuwe matcher
        Matcher<TestObject> testObjectMatcher = new Matcher<>(TestObject.class);



        // maak een paar query parameters
        ArrayList<QueryParam> queryParams = new ArrayList<>();
        // goede qparameter
        queryParams.add(new QueryParam<String>("name", "jo"));

        // slechte queryparameter
        queryParams.add(new QueryParam<String>("invalid field", "invalid value"));


        // maak een lijst met test objecten om te filteren.
        ArrayList<TestObject> testObjects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testObjects.add(new TestObject("CORRECT"));
            testObjects.add(new TestObject("INCORRECT"));
        }

        // er zitten nu 20 objecten in de lijst
        assertThat(testObjects.size(), is(20));


        // een query om op te matchen
        QueryParam<String> qp = new QueryParam<String>("name", "CORRECT");

        QueryResult result = testObjectMatcher.getResult(qp, testObjects);

        assertThat(result.getResults().size(), is(10));
        assertThat(result.getErrors().size(), is(0));






    }




    @Test
    public void testGetValidQueries(){



        // maak nieuwe matcher
        Matcher<TestObject> testObjectMatcher = new Matcher<>(TestObject.class);



        // maak een paar query parameters
        ArrayList<QueryParam> queryParams = new ArrayList<>();
        // goede qparameter
        QueryParam<String> q = new QueryParam("name", "jo");
        queryParams.add(q);

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

    @Test
    public void matchTest(){


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

        try {
            testObjectMatcher.match(testObject2, methodParamMap);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
