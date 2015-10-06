package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by jonathan on 6-10-15.
 */
public class QueryError<T> implements Error{


    private final QueryParam queryParam;

    private final Class<T> compareClass;



    public QueryError(Class<T> compareClass, final QueryParam queryParam) {
        this.compareClass = compareClass;
        this.queryParam = queryParam;
    }


    @Override
    public String getReason() {

        return "the object: "+ compareClass.getSimpleName() + " doesn't have a field with name: " + queryParam.getName();
    }

    @Override
    public String toString() {
        return getReason();
    }
}
