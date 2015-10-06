package model;

import java.util.Objects;

/**
 * Created by jonathan on 6-10-15.
 */
public class QueryError<T> implements Error{


    private final QueryParam queryParam;
    private final T object;

    public QueryError(final QueryParam queryParam, final T object) {

        this.object = object;
        this.queryParam = queryParam;
    }


    @Override
    public String getReason() {

        return "the object: "+ object.getClass().getSimpleName() + " doesn't have a field with name: " + queryParam.getName();
    }

    @Override
    public String toString() {
        return getReason();
    }
}
