package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by falco on 30-9-15.
 */
@XmlRootElement
public class Movies {

    public List<Movie> movies;

    public Movies() {
        movies = new ArrayList<>();
        movies.add(new Movie(1,1, "KANKER DATE", "OK","ok", "OKEE", 10000 ));
    }
    @XmlElement
    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
    public void addMovies(Movie movie){
        this.movies.add(movie);
    }
}
