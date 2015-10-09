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
    }

    public void addMovie(final Movie m){
        movies.add(m);
    }

    public AccessToken giveUserAccessToken(User user){
        AccessToken accessToken = getAccessTokenFromUser(user);
        if(accessToken == null){
            accessToken = new AccessToken(user, (accessTokens.size()+1)*12-1);
            accessTokens.add(accessToken);
        }
        return accessToken;
    }
    /**
     *
     * @param u the user
     * @return null if no accessToken is assigned to the user of the accessToken of that user
     */
    private AccessToken getAccessTokenFromUser(User u){
        for(AccessToken t : accessTokens){
            if(t.getUser().equals(u)){
                return t;
            }
        }
        return null;
    }

    /**
     *
     * @param token the token
     * @return null if the token is not correct or the user with the given token
     */
    public User getUserWithAccessToken(String token){
        for(AccessToken t : accessTokens){
            if(t.getToken().equals(token)){
                return t.getUser();
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





}

    

