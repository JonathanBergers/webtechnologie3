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
    private ArrayList<AccessToken> accessTokens = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Rating> ratings = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public void addUser(final User u){
        users.add(u);
    }

    public void addRating(final Rating r){
        ratings.add(r);

        r.getUser().addRating(r);
    }

    public void addMovie(final Movie m){
        movies.add(m);
    }




    /**
     *
     * @param token the token
     * @return null if the token is not correct or the user with the given token
     */
    public User getUserWithAccessToken(String token){
        for(User t : getUsers()){
            if(t.hasToken(token)){
                return t;
            }
        }
        return null;
    }






    public User getUserByName(final String name){
        for (User u: getUsers()){
            if(u.hasFirstName(name)){
                return u;
            }
        }
        return null;
    }



    public ArrayList<Movie> getMoviesWithRating(){

        ArrayList<Movie> moviesWithRating = new ArrayList<>();
        for(Movie m: getMovies()){
            if(m.hasRating()){
                moviesWithRating.add(m);
            }
        }
        return moviesWithRating;

    }

    public ArrayList<Rating> getRatingsWithUser(User u){

        ArrayList<Rating> ratingswithUsers = new ArrayList<>();
        for(Rating m: getRatings()){
            if(m.hasUser(u)){
                ratingswithUsers.add(m);
            }
        }
        return ratingswithUsers;

    }


    public Movie getMovieByTT(int tt) {

        for (Movie m : getMovies()) {
            if (m.hasTT(tt)) {
                return m;
            }
        }
        return null;
    }



}

    

