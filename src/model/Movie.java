package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by jonathan on 30-9-15.
 */
@XmlRootElement
public class Movie {

    private int id;
    private int tt;

    private String releaseDate;
    private String titel;
    private String discription;
    private String director;
    private int length;

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
}
