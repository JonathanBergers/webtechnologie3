package test;

import matching.Matcher;
import matching.QueryParameter;
import matching.QueryResult;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;
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
        ArrayList<QueryParameter> queryParameters = new ArrayList<>();
        // goede qparameter
        queryParameters.add(new QueryParameter<String>("name", "jo"));

        // slechte queryparameter
        queryParameters.add(new QueryParameter<String>("invalid field", "invalid value"));


        // maak een lijst met test objecten om te filteren.
        ArrayList<TestObject> testObjects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testObjects.add(new TestObject("CORRECT"));
            testObjects.add(new TestObject("INCORRECT"));
        }

        // er zitten nu 20 objecten in de lijst
        assertThat(testObjects.size(), is(20));


        // een query om op te matchen
        QueryParameter<String> qp = new QueryParameter<String>("name", "CORRECT");

        QueryResult<TestObject> result = testObjectMatcher.getResult(qp, testObjects);

        assertThat(result.getResults().size(), is(10));
        assertThat(result.getErrors().size(), is(0));



        for(TestObject t: result.getResults()){
            assertTrue(t.getName().equals("CORRECT"));
        }



    }




    @Test
    public void testGetValidQueries(){



        // maak nieuwe matcher
        Matcher<TestObject> testObjectMatcher = new Matcher<>(TestObject.class);



        // maak een paar query parameters
        ArrayList<QueryParameter<?>> queryParameters = new ArrayList<>();
        // goede qparameter
        QueryParameter<String> q = new QueryParameter("name", "jo");
        queryParameters.add(q);

        // slechte queryparameter
        queryParameters.add(new QueryParameter("invalid field", "invalid value"));


        // maak een nieuw test object om mee te matchen
        TestObject testObject = new TestObject("jonathan");

        // kijk of de query parameters overeenkomen met de attributen van het testobject.
        // als het goed is zal er maar een item in de map moeten zitten en zal er een error in de matcher zijn
        HashMap<QueryParameter, Method> methodParamMap = testObjectMatcher.getValidQueries(new HashMap<QueryParameter, Method>(), queryParameters);


        assertThat(methodParamMap.size(), is(1));
        assertThat(testObjectMatcher.getErrors().size(), is(1));




    }

    @Test
    public void testGetValidQueriesAlleenStringOfInt(){



        // maak nieuwe matcher
        Matcher<TestObject2> testObjectMatcher = new Matcher<>(TestObject2.class);



        // maak een paar query parameters
        ArrayList<QueryParameter<?>> queryParameters = new ArrayList<>();
        // goede qparameter
        queryParameters.add(new QueryParameter<String>("name", "jo"));

        // slechte queryparameter
        queryParameters.add(new QueryParameter<String>("invalid field", "invalid value"));


        // maak een nieuw test object om mee te matchen
        TestObject testObject = new TestObject("jonathan");
        TestObject2 testObject2 = new TestObject2("bergers", testObject);

        // kijk of de query parameters overeenkomen met de attributen van het testobject.
        // als het goed is zal er maar een item in de map moeten zitten en zal er een error in de matcher zijn
        // het testObject wordt niet meegenomen omdat de matcher alleen maar op strings of integers kan matchen
        HashMap<QueryParameter, Method> methodParamMap = testObjectMatcher.getValidQueries(new HashMap<QueryParameter, Method>(), queryParameters);


        assertThat(methodParamMap.size(), is(1));
        assertThat(testObjectMatcher.getErrors().size(), is(1));



    }

    @Test
    public void matchTest(){


        // maak nieuwe matcher
        Matcher<TestObject2> testObjectMatcher = new Matcher<>(TestObject2.class);



        // maak een paar query parameters
        ArrayList<QueryParameter<?>> queryParameters = new ArrayList<>();
        // goede qparameter
        queryParameters.add(new QueryParameter<String>("name", "jo"));

        // slechte queryparameter
        queryParameters.add(new QueryParameter<String>("invalid field", "invalid value"));


        // maak een nieuw test object om mee te matchen
        TestObject testObject = new TestObject("jonathan");
        TestObject2 testObject2 = new TestObject2("bergers", testObject);

        // kijk of de query parameters overeenkomen met de attributen van het testobject.
        // als het goed is zal er maar een item in de map moeten zitten en zal er een error in de matcher zijn
        // het testObject wordt niet meegenomen omdat de matcher alleen maar op strings of integers kan matchen
        HashMap<QueryParameter, Method> methodParamMap = testObjectMatcher.getValidQueries(new HashMap<QueryParameter, Method>(), queryParameters);

        try {
            testObjectMatcher.match(testObject2, methodParamMap);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
