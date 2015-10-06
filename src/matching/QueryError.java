package matching;

/**
 * Created by jonathan on 6-10-15.
 */
public class QueryError<T> extends matching.Error {


    private final QueryParameter queryParameter;

    private final Class<T> compareClass;



    public QueryError(Class<T> compareClass, final QueryParameter queryParameter) {
        this.compareClass = compareClass;
        this.queryParameter = queryParameter;
    }




    @Override
    public String toString() {
        return "the object: "+ compareClass.getSimpleName() + " doesn't have a field with name: " + queryParameter.getName();
    }
}
