package matching;

/**
 * Created by jonathan on 6-10-15.
 */
public class QueryParameter<T> {

    private final String name;
    private final T value;


    public QueryParameter(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }
}


