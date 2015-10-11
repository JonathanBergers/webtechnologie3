package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by falco on 6-10-15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Rating{

    private int stars;
    private User user;
    private Movie movie;


    public Rating(int stars, User user, Movie movie) {
        setStars(stars);

        this.user = user;
        this.movie = movie;

    }

    public Rating(){

    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        if(stars<1){
            this.stars = 1;
        } else if(stars>10){
            this.stars = 10;
        } else {
            this.stars = stars;
        }

    }

    @JsonIgnore
    @XmlTransient
    public User getUser(){
        return this.user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public boolean hasUser(User u) {
        return getUser().equals(u);
    }


    @Override
    public boolean equals(Object obj) {

        if(obj == this) return true;
        if(!(obj instanceof Rating)) return false;
        Rating r = (Rating) obj;
        if(!r.hasUser(getUser()))return false;
        return (r.getMovie().equals(getMovie()));

    }
}
