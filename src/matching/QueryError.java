package matching;

/**
 * Created by jonathan on 6-10-15.
 */
public class QueryError<T> extends Error {




    private final QueryParameter queryParameter;

    private final Class<T> compareClass;




    public QueryError(Class<T> compareClass, final QueryParameter queryParameter) {
        this.compareClass = compareClass;
        this.queryParameter = queryParameter;

        errorMessage = "the object: "+ compareClass.getSimpleName() + " doesn't have a field with name: " + queryParameter.getName();
    }





    @Override
    public String toString() {
        return "the class: "+ compareClass.getSimpleName() + " doesn't have a field with name: " + queryParameter.getName();
    }
}
