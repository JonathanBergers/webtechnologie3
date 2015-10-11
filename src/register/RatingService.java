package register;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import model.Model;
import model.Movie;
import model.Rating;
import model.User;

/**
 * Created by jonathan on 9-10-15.
 */
public class RatingService {




    /**
     * checks if the credentials given are correct
     * if they are, creates the User
     *
     * @return
     */
    public static CustomRestResponse validateUserCredentials(CustomRestResponse responseMessage, JsonObject jsonObject, Model model, User user) {



        JsonPrimitive idObject = jsonObject.getAsJsonPrimitive("tt");
        JsonPrimitive ratingObject = jsonObject.getAsJsonPrimitive("rating");

        int movieId ;
        int rating ;

        if(idObject == null){
            responseMessage.addError("FIELD1", "MOVIE_TT_MISSING");
        }
        if(ratingObject == null){
            responseMessage.addError("FIELD2", "RATIG_MISSING");
        }

        if(!responseMessage.isSuccess()){
            return responseMessage;
        }

        String movieIdString = idObject.getAsString();
        String ratingString  = ratingObject.getAsString();


        if(ratingString.matches("-?\\d+(\\.\\d+)?")){


            return responseMessage.addError("Rating1", "rating is not a number");
        }else{
            rating = Integer.parseInt(ratingString);
            if(rating<= 0 || rating> 5){
                return responseMessage.addError("Rating2", "rating must be between 0 and 5");
            }


        }
        if(movieIdString.matches("-?\\d+(\\.\\d+)?")){


            return responseMessage.addError("Rating1", "rating is not a number");
        }else{
            movieId = Integer.parseInt(ratingString);


        }

        Movie m = model.getMovieByTT(movieId);

        if(m == null){
            return responseMessage.addError("TT", "no movie found with tt : " + movieId);
        }

        Rating theRating = new Rating(rating, user, m);

        if(user.hasRating(theRating)){
            responseMessage.addMessage("Rating", "UPDATED");
        }else{
            responseMessage.addMessage("Rating", "ADDED" );

        }

        model.addRating(theRating);

        return responseMessage;




    }


}