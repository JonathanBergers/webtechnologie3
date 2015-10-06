package model;

import java.util.ArrayList;

/**
 * Created by jonathan on 6-10-15.
 */
public class QueryResult<T> {


    private ArrayList<T> results = new ArrayList<>();

    private ArrayList<QueryError<T>> errors = new ArrayList<>();

    public QueryResult(ArrayList<T> results, ArrayList<QueryError<T>> errors) {
        this.results = results;
        this.errors = errors;
    }

    public void addError(final QueryError<T> queryError){

        errors.add(queryError);
    }

    public void addResult(final T result){

        results.add(result);
    }

}
