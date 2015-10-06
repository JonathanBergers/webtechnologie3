package model;

import com.sun.deploy.util.ArrayUtil;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;

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




    public ArrayList<User>getUserByFirstname(final String firstName){

        ArrayList<User> usersWithNickname = new ArrayList<>();

        for(User u : getUsers()){
            if(u.hasFirstName(firstName)){
                usersWithNickname.add(u);
            }
        }
        return usersWithNickname;
    }

    public ArrayList<User>getUserByNickName(final String nickName){

        ArrayList<User> usersWithNickname = new ArrayList<>();

        for(User u : getUsers()){
            if(u.hasNickName(nickName)){
                usersWithNickname.add(u);
            }
        }




        return usersWithNickname;
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

    

