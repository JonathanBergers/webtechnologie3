package model;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 30-9-15.
 */
@XmlRootElement
public class Model {

    public Model() {

    }

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }


    public void addUser(final User u){
        users.add(u);
    }

    public void addMovie(final Movie m){
        movies.add(m);
    }




    public User getUserByFirstName(final String firstName){

        for(User u : getUsers()){
            if(u.hasFirstName(firstName)){
                return u;
            }
        }
        return null;
    }

    public User getUserByNickName(final String nickName){

        for(User u : getUsers()){
            if(u.hasFirstName(nickName)){
                return u;
            }
        }
        return null;
    }
    public ArrayList<Movie> getMoviesByTitle(final String title){
        ArrayList<Movie> r = new ArrayList<>();
        for(Movie m : getMovies()){
            if(m.getTitel().toLowerCase().contains(title.toLowerCase())){
                r.add(m);
            }
        }
        return r;
    }







}

    

