package model;

import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 30-9-15.
 */
public class Model {


    private List<User> users;

    public Model() {
        this.users = new ArrayList<>();
    }

    @XmlElementWrapper(name = "users")
    public List<User> getUsers() {
        return users;
    }

    public void addUser(final User u){

        assert !users.contains(u): "user already exists";
        users.add(u);
    }
}
