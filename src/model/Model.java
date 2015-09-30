package model;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 30-9-15.
 */
@XmlRootElement
public class Model {

    public Movies movies;
    private List<User> users;

    public Model() {
        this.users = new ArrayList<>();
        this.movies = new Movies();
    }

    @XmlElementWrapper(name = "users")
    public List<User> getUsers() {
        return users;
    }

    public void addUser(final User u){

        assert !users.contains(u): "user already exists";
        users.add(u);
    }

    @XmlElementWrapper(name = "movies")
    public Movies getMovies(){
        return movies;
    }
}
