package matching;

import com.sun.xml.txw2.annotation.XmlElement;
import matching.Error;
import matching.QueryError;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by jonathan on 6-10-15.
 */
public class QueryResult<T> {


    private ArrayList<T> results = new ArrayList<>();

    private ArrayList<matching.Error> errors = new ArrayList<>();

    public QueryResult(ArrayList<T> results, ArrayList<Error> errors) {
        this.results = results;
        this.errors = errors;
    }

    public void addError(final QueryError<T> queryError){

        errors.add(queryError);
    }

    public void addResult(final T result){

        results.add(result);
    }


    public ArrayList<T> getResults() {
        return results;
    }

    public ArrayList<Error> getErrors() {
        return errors;
    }

    public void setResults(ArrayList<T> results) {
        this.results = results;
    }

    public void setErrors(ArrayList<Error> errors) {
        this.errors = errors;
    }
}
