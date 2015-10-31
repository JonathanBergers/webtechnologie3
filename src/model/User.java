package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.*;

/**
 * Created by jonathan on 30-9-15.
 *
 o Achternaam
 o Tussenvoegsels
 o Voornaam
 o Nickname
 o Wachtwoord
 *

 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class User {


    private String firstname, lastname, infix, password, email;
    private AccessToken accessToken;
    private ArrayList<Rating> ratings =  new ArrayList<>();


    public User(String firstname, String lastname, String infix, String password, String email) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.infix = infix;
        this.password = password;
        this.email = email;
        generateToken();
    }

    public User(String firstname, String lastname, String password, String email) {
        this(firstname, lastname, "", password, email);
    }



    public User() {
    }


    private void generateToken(){
        this.accessToken = new AccessToken(getFirstname() + "TOKEN");
    }



    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getInfix() {
        return infix;
    }

    @XmlTransient
    @JsonIgnore
    public String getPassword() {
        return password;
    }



    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setInfix(String infix) {
        this.infix = infix;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        String toString = getFirstname();
        if(getInfix() == null){

            toString += " " + getLastname();

        }

        toString += " " + getInfix() + " " + getLastname();

        return toString;
    }


    public boolean hasFirstName(final String name){

        return getFirstname().equals(name);
    }



    @JsonIgnore
    @XmlTransient
    public ArrayList<Rating> getRatings() {
        return ratings;
    }


    public void addRating(Rating rating) {

        for(Rating r : ratings){
            if(r.getMovie().getTitel().equals(rating.getMovie().getTitel())){
                ratings.get(ratings.indexOf(r)).setStars(rating.getStars());
                return;
            }
        }
       ratings.add(rating);

    }
    public void deleteRating(Rating rating){
        ratings.remove(rating);
    }

    public boolean hasPassword(String password){
        return getPassword().equals(password);
    }


    @JsonIgnore
    @XmlTransient
    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public boolean hasToken(String token) {
        return getAccessToken().equals(token);
    }

    public boolean hasRating(Rating rating){

        for(Rating r: getRatings()){
            if(r.getMovie().getId() == rating.getMovie().getId()) return true;
        }
        return false;


    }

    @Override
    public boolean equals(Object obj) {
        return (getFirstname()+getLastname()).equals(obj);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }
}
