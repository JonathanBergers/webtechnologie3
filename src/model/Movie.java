package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;


/**
 * Created by jonathan on 30-9-15.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Movie {

    public final static int NOTRATED = -1;

    private int id;
    private int tt;

    private String releaseDate;
    private String titel;
    private String discription;
    private String director;
    private int length;

    private int overAllRating = NOTRATED;
    private ArrayList<Rating> ratings =  new ArrayList<>();

    public Movie(int id, int tt, String releaseDate, String titel, String discription, String director, int length) {
        this.id = id;
        this.tt = tt;
        this.releaseDate = releaseDate;
        this.titel = titel;
        this.discription = discription;
        this.director = director;
        this.length = length;
    }

    public Movie() {

    }
    @JsonIgnore
    @XmlTransient
    public int getId() {
        return id;
    }

    public int getTt() {
        return tt;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitel() {
        return titel;
    }

    public String getDiscription() {
        return discription;
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTt(int tt) {
        this.tt = tt;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @JsonIgnore
    @XmlTransient
    public ArrayList<Rating> getRatings() {return ratings;}

    public void setRatings(ArrayList<Rating> ratings) {this.ratings = ratings;}

    public int getOverAllRating() {return overAllRating;}

    public void setOverAllRating(int overAllRating) {overAllRating = overAllRating;}

    public void Rate(int stars, User user){
        Rating rating = new Rating(stars, user, this);
        for(Rating r : ratings){
            if(r.getUser().equals(rating.getUser())){
                rating.getUser().addRating(rating);
                ratings.set(ratings.indexOf(r), rating);
                System.out.println("Rating changed to "+rating.getStars()+ " by User: " + rating.getUser().getFirstname());
                calculateOverallRating();
                return;
            }
        }
        rating.getUser().addRating(rating);
        ratings.add(rating);
        System.out.println("rating added of "+ rating.getStars()+ " by User: " + rating.getUser().getFirstname());
        calculateOverallRating();

    }
    private void calculateOverallRating(){
        System.out.println("List of ratings: ");
        for(Rating r : ratings){
            System.out.println("stars = "+r.getStars());
        }
        if(ratings.size()<3){
            overAllRating = NOTRATED;
            System.out.println("OverallRating = "+overAllRating+"\n");
            return;
        }
        int i = 0;
        int totalStars = 0;
        for(Rating r: ratings){
            totalStars+=r.getStars();
            i++;
        }
        overAllRating = Math.round(totalStars/(i));
        System.out.println("OverallRating = "+overAllRating+"\n");
    }
    public void deleteRatingFromUser(User user){
        for (int i = 0; i < ratings.size(); i++) {
            if(ratings.get(i).getUser().equals(user)){
                System.out.println("Delete Rating of " + ratings.get(i).getStars() + " Stars" + " by User: " + ratings.get(i).getUser().getFirstname());
                user.deleteRating(ratings.get(i));
                ratings.remove(i);
                break;
            }
        }
        calculateOverallRating();
    }
}
