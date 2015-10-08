package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;

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
@XmlAccessorType(XmlAccessType.FIELD)
public class User {


    private String nickname, firstname, lastname, infix, password;
    private ArrayList<Rating> ratings =  new ArrayList<>();

    public User(String nickname, String firstname, String lastname, String infix, String password) {
        this.nickname = nickname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.infix = infix;
        this.password = password;
    }

    public User(String nickname, String firstname, String lastname, String password) {
        this.nickname = nickname;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }



    public User() {
    }


    public String getNickname() {
        return nickname;
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

    public String getPassword() {
        return password;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
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
        String toString = getNickname() + ": " + getFirstname();
        if(getInfix() == null){

            toString += " " + getLastname();

        }

        toString += " " + getInfix() + " " + getLastname();

        return toString;
    }


    public boolean hasFirstName(final String name){

        return getFirstname().equals(name);
    }

    public boolean hasNickName(final String nickName){

        return getNickname().equals(nickName);
    }

    @JsonIgnore
    @XmlTransient
    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public void addRating(Rating rating) {
        for(Rating r : ratings){
            if(r.getMovie().equals(rating.getMovie())){
                r = rating;
                return;
            }
        }
        this.ratings.add(rating);
    }
    public void deleteRating(Rating rating){
        ratings.remove(rating);
    }

    public boolean hasPassword(String password){
        return getPassword().equals(password);
    }
}
