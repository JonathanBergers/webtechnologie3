package model;

/**
 * Created by jonathan on 6-10-15.
 */
public class QueryParam {

    private final String name, value;


    public QueryParam(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}


