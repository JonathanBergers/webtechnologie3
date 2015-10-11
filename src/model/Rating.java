package model;

/**
 * Created by falco on 6-10-15.
 */
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


    public User getUser(){
        return this.user;
    }

    public Movie getMovie() {
        return movie;
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
